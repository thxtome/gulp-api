
package gulp.rest.alarm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gulp.rest.alarm.model.Alarm;
import gulp.rest.alarm.service.AlarmService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AlarmController {
	
	private final AlarmService AlarmService;

	@GetMapping("/alarms")
	@ApiOperation(value = "알림 조회", notes = "사용자에게 요일 조건을 받아서 알림을 조회한다.")
	public ResponseEntity<Object> getAlarms(String day) {
		return null;
	}
	
	@GetMapping("/alarms/{alarm_id}")
	@ApiOperation(value = "알림 단건 조회", notes = "사용자에게 알림의 아이디값을 받아서 알림을 조회한다.")
	public ResponseEntity<Object> getAlarm(@PathVariable("alarm_id") Long id ) {
		return null;
	}
	
	@PostMapping("/alarm")
	@ApiOperation(value ="알림 생성", notes = "사용자에게 약의 정보를 받아서 약을 생성한다.")
	@ApiImplicitParam(
		name = "alarm", 
		value = "sample\n\n " + "{\"time\":\"20:30:00\", \"day\\\":\"135\", \"medicines\":[1,3,6]}",
		required = true, 
		dataTypeClass = Alarm.class, 
		paramType = "body")
	public ResponseEntity<Object> createAlarm(@RequestBody Alarm alarm) {
		return null;
	}
	
	@PutMapping("/alarm")
	@ApiOperation(value = "알림 수정", notes = "사용자에게 약의 정보를 받아서 약을 수정한다.")
	@ApiImplicitParam(
		name = "alarm", 
		value = "sample\n\n " + "{\"alarmId\":1, \"time\":\"20:30:00\", \"day\":\"135\", \"medicines\":[1,3,6]}",
		required = true, 
		dataTypeClass = Alarm.class, 
		paramType = "body")
	public ResponseEntity<Object> createMember(@RequestBody Alarm alarm) {
		return null;
	}
}
