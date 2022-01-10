package gulp.rest.alarm.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

import gulp.rest.medicine.model.Medicine;
import gulp.rest.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmHist {
	@Id
	@GeneratedValue
	@Column(name = "alarm_hist_id")
	private Long id;
	
	private LocalDateTime created_at;
	
	@ManyToOne
	@JoinColumn(name = "alarm_id")
	private Alarm alarm;
	
	@OneToMany(mappedBy = "alarmHist", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AlarmHistMedicine> alarmHistMedicines = new ArrayList<>();
	
	public AlarmHist create(Alarm alarm, List<Medicine> medicineList) {
		this.alarm = alarm;
		this.alarmHistMedicines = medicineList.stream()
				.map((medicine) -> new AlarmHistMedicine().create(this, medicine))
				.collect(Collectors.toList());
		return this;
	}
}
