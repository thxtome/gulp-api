package gulp.rest.alarm.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gulp.rest.alarm.model.AlarmHist;

@Repository
public interface AlarmHistRepository extends JpaRepository <AlarmHist, Long> {
	AlarmHist findByAlarmIdAndCreatedAtAfter(Long alarmId, LocalDateTime today);
}
