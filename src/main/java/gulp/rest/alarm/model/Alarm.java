package gulp.rest.alarm.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import gulp.rest.alarm.dto.AlarmForm;
import gulp.rest.medicine.model.Medicine;
import gulp.rest.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Alarm {
	@Id
	@GeneratedValue
	@Column(name = "alarm_id")
	private Long id;

	private LocalTime time;

	private String day;

	@ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id") // (2)
	private Member member;

	@OneToMany(mappedBy = "alarm", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<AlarmMedicine> alarmMedicines = new ArrayList<>();

	@Column(columnDefinition="BOOLEAN DEFAULT false")
	private boolean isRemoved;
	
	@Column(columnDefinition="BOOLEAN DEFAULT false")
	private boolean isEaten; 
	
	@Column(columnDefinition="BOOLEAN DEFAULT false")
	private boolean isPushed; 
	
	public Alarm create(AlarmForm alarmForm, Long memberId) {
		this.day = alarmForm.getDay();
		this.time = alarmForm.getTime();
		this.alarmMedicines = alarmForm.getMedicineIdList().stream()
				.map((medicineId) -> new AlarmMedicine().create(this, Medicine.builder().id(medicineId).build()))
				.collect(Collectors.toList());
		this.member = Member.builder().id(memberId).build();
		
		LocalDateTime currentDateTime = LocalDateTime.now();  
		if(day.contains(String.valueOf(currentDateTime.getDayOfWeek().getValue()))) {
			LocalTime currentTime = LocalTime.now();
			this.isPushed = this.time.isBefore(currentTime);
		}
		return this;
	}
	
	public void update(AlarmForm alarmForm) {
		this.day = alarmForm.getDay();
		this.time = alarmForm.getTime();
		this.alarmMedicines.clear();
		this.alarmMedicines.addAll(alarmForm.getMedicineIdList().stream()
				.map((medicineId) -> new AlarmMedicine().create(this, Medicine.builder().id(medicineId).build()))
				.collect(Collectors.toList()));
		this.isEaten = false;
		this.isPushed = false;
	}
	
	public void delete() {
		this.isRemoved = true;
		this.isPushed = true;
	}
	
	public AlarmHist dose() {
		this.isEaten = true;
		AlarmHist alarmHist = new AlarmHist().create(this, this.alarmMedicines.stream().map((alarmMedicine)->{return alarmMedicine.getMedicine();}).collect(Collectors.toList()));
		return alarmHist;
	}
	
	public void cancelDose() {
		this.isEaten = false;
	}
	
	public Alarm reset() {
		this.isPushed = false;
		this.isEaten = false;
		return this;
	}

	public Long getId() {
		return this.id;
	}

}
