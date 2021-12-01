package gulp.rest.alarm.model;

import javax.persistence.Entity;
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
	private Long alarmMedicineId;
	
	private String day;
	
	@ManyToOne
	@JoinColumn(name = "alarm_id")
	private Alarm alarm;
	
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	private Medicine medicine;
}
