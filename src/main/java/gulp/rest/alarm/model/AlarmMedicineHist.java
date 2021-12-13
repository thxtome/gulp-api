package gulp.rest.alarm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AlarmMedicineHist {
	@Id @GeneratedValue
	@Column(name = "alarm_medicine_hist_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "alarm_medicine_id")
	private AlarmMedicine alarmMedicine;
	
	private LocalDateTime createdAt; 
	
	@Column(columnDefinition="BOOLEAN DEFAULT false")
	private boolean isEaten; 
}
