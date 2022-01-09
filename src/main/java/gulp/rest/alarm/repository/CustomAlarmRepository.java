package gulp.rest.alarm.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gulp.rest.alarm.dto.AlarmDto;

@Repository
public interface CustomAlarmRepository {
	
	List<AlarmDto> findAllByMemberIdAndDayContains(@Param("memberId") Long memberId, @Param("day") String day);

	AlarmDto findAllByIdAndMemberId(@Param("memberId") Long memberId, @Param("alarmId") Long alarmId);
}
