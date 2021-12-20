package gulp.rest.alarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gulp.rest.alarm.model.AlarmHist;

@Repository
public interface AlarmHistRepository extends JpaRepository <AlarmHist, Long> {
	
}
