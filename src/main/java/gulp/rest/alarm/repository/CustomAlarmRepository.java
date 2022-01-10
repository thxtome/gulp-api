package gulp.rest.alarm.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gulp.rest.alarm.dto.AlarmDto;

@Repository
public interface CustomAlarmRepository {
	
	List<AlarmDto> findAllDtoByMemberIdAndDayContains(@Param("memberId") Long memberId, @Param("day") String day);

	AlarmDto findDtoByIdAndMemberId(@Param("memberId") Long memberId, @Param("alarmId") Long alarmId);
}
