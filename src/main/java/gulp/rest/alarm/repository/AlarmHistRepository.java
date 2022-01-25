package gulp.rest.alarm.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gulp.rest.alarm.model.AlarmHist;

@Repository
public interface AlarmHistRepository extends JpaRepository <AlarmHist, Long>, CustomAlarmHistRepository {
	AlarmHist findByAlarmIdAndCreatedAtAfter(Long alarmId, LocalDateTime today);
	
//	@Query("select ah from AlarmHist ah join ah.alarm a join a.member m join fetch ah.alarmHistMedicines where m.id = :memberId and function('date_format', ah.createdAt, '%Y%m%d') = :searchDay")
//	AlarmHist findByAlarmIdAndCreatedAt(@Param("memberId") Long memberId, @Param("searchDay") String searchDay);
}
