package gulp.rest.alarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gulp.rest.alarm.model.Alarm;

@Repository
public interface CustomAlarmRepository {
	
	List<Alarm> findAllByMemberIdAndDayContains(@Param("memberId") Long memberId, @Param("day") String day);

	Alarm findAllByIdAndMemberId(@Param("memberId") Long memberId, @Param("alarmId") Long alarmId);
}
