package gulp.rest.alarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gulp.rest.alarm.model.Alarm;

@Repository
public interface AlarmRepository extends JpaRepository <Alarm, Long>, CustomAlarmRepository {
}
