package gulp.rest.alarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gulp.rest.alarm.model.Alarm;

@Repository
public interface AlarmRepository extends JpaRepository <Alarm, Long>, CustomAlarmRepository {
	
	@Query("SELECT distinct a FROM Alarm a join fetch a.alarmMedicines join a.member m WHERE a.id = :alarmId AND m.id = :memberId")
	Alarm findByIdAndMemberId(@Param("memberId") Long memberId, @Param("alarmId") Long alarmId);
}
