package gulp.rest.alarm.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@Column(columnDefinition="BOOLEAN DEFAULT false")
	private boolean isEaten; 
	
	@Column(columnDefinition="BOOLEAN DEFAULT false")
	private boolean isPushed; 
	
	private LocalDateTime created_at;
	
	@ManyToOne
	@JoinColumn(name = "alarm_id")
	private Alarm alarm;
	
	public AlarmHist create(Alarm alarm) {
		this.alarm = alarm;
		return this;
	}
}
