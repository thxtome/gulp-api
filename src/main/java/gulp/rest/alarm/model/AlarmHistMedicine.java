package gulp.rest.alarm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import gulp.rest.medicine.model.Medicine;
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
	private LocalDateTime createdAt; 
	
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	private Medicine medicine;
}
