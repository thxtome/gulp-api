package gulp.rest.alarm.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Alarm {
	@Id @GeneratedValue
	@Column(name = "alarm_id")
	private Long id;
	
	private LocalTime time;
	
	private String day;
	
	@OneToMany(mappedBy = "alarm", fetch = FetchType.LAZY)
	private List<AlarmMedicine> alarmMedicines;
}
