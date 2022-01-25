package gulp.rest.alarm.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gulp.rest.alarm.dto.AlarmHistDto;

@Repository
public interface CustomAlarmHistRepository {
	
	List<AlarmHistDto> findByAlarmIdAndCreatedAt(@Param("memberId") Long memberId, @Param("searchDay") String searchDay);
}
