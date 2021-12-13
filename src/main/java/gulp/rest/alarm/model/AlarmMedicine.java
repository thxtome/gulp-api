package gulp.rest.alarm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "alarm_id")
	private Alarm alarm;
	
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	private Medicine medicine;
	
	public AlarmMedicine create(Alarm alarm, Medicine medicine) {
		this.medicine = medicine;
		this.alarm = alarm;
		return this;
		
	}
}
