package gulp.rest.alarm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import gulp.rest.medicine.model.Medicine;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AlarmMedicine {
	@Id @GeneratedValue
	@Column(name = "alarm_medicine_id")
	private Long id;
	
	@ManyToOne(targetEntity = Alarm.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "alarm_id")
	private Alarm alarm;
	
	@ManyToOne(targetEntity = Medicine.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "medicine_id")
	private Medicine medicine;
	
	public AlarmMedicine create(Alarm alarm, Medicine medicine) {
		this.medicine = medicine;
		this.alarm = alarm;
		return this;
	}
}
