package gulp.rest.alarm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class AlarmHistMedicine {
	@Id @GeneratedValue
	@Column(name = "alarm_hist_medicine_id")
	private Long id;
	
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne(targetEntity = AlarmHist.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "alarm_hist_id") // (2)
	private AlarmHist alarmHist;
	
	@ManyToOne(targetEntity = Medicine.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "medicine_id")
	private Medicine medicine;
	
	public AlarmHistMedicine create(AlarmHist alarmHist, Medicine medicine) {
		this.medicine = medicine;
		this.alarmHist = alarmHist;
		return this;
	}
}
