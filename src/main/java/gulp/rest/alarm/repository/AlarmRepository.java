package gulp.rest.alarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gulp.rest.alarm.model.Alarm;

@Repository
public interface AlarmRepository extends JpaRepository <Alarm, Long> {
	
	@Query("SELECT distinct a FROM Alarm a join fetch a.alarmMedicines join a.member m WHERE a.day Like %:day% AND m.id = :memberId")
	List<Alarm> findAllByMemberIdAndDayContains(@Param("memberId") Long memberId, @Param("day") String day);

	@Query("SELECT distinct a FROM Alarm a join fetch a.alarmMedicines join a.member m WHERE a.id = :alarmId AND m.id = :memberId")
	Alarm findAllByIdAndMemberId(@Param("memberId") Long memberId, @Param("alarmId") Long alarmId);
}
