
package gulp.rest.alarm;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gulp.rest.alarm.dto.AlarmForm;
import gulp.rest.alarm.service.AlarmService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AlarmController {
	
	private final AlarmService alarmService;

	@GetMapping("/alarms")
	@ApiOperation(value = "알림 조회", notes = "사용자에게 요일 조건을 받아서 알림을 조회한다.")
	public ResponseEntity<Object> getAlarms(Authentication authentication, String day) {
		return alarmService.getAlarmList((Long)authentication.getPrincipal(), day);
	}
	
	@GetMapping("/alarms/{alarm_id}")
	@ApiOperation(value = "알림 단건 조회", notes = "사용자에게 알림의 아이디값을 받아서 알림을 조회한다.")
	public ResponseEntity<Object> getAlarm(Authentication authentication, @PathVariable("alarm_id") Long alarmId ) {
		return alarmService.getAlarm((Long)authentication.getPrincipal(), alarmId);
	}
	
	@PostMapping("/alarm")
	@ApiOperation(value ="알림 생성", notes = "사용자에게 약의 정보를 받아서 약을 생성한다.")
	@ApiImplicitParam(
		name = "alarmForm", 
		value = "sample\n\n " + "{\"time\":\"20:30:00\", \"day\\\":\"135\", \"medicineIdList\":[1,3,6]}",
		required = true, 
		dataTypeClass = AlarmForm.class, 
		paramType = "body")
	public ResponseEntity<Object> createAlarm(@RequestBody AlarmForm alarmForm, Authentication authentication) {
		return alarmService.createAlarm(alarmForm, (Long)authentication.getPrincipal());
	}
	
	@PutMapping("/alarm")
	@ApiOperation(value = "알림 수정", notes = "사용자에게 약의 정보를 받아서 약을 수정한다.")
	@ApiImplicitParam(
		name = "alarmForm", 
		value = "sample\n\n " + "{\"id\":1, \"time\":\"20:30:00\", \"day\":\"135\", \"medicineIdList\":[1,3,6]}",
		required = true, 
		dataTypeClass = AlarmForm.class, 
		paramType = "body")
	public ResponseEntity<Object> updateAlarm(@RequestBody AlarmForm alarmForm, Authentication authentication) {
		return alarmService.updateAlarm(alarmForm);
	}
	
	@DeleteMapping("/alarms/{alarm_id}")
	@ApiOperation(value = "알림 삭제", notes = "사용자에게 약의 정보를 받아서 약을 삭제한다.")
	public ResponseEntity<Object> deleteAlarm(@PathVariable("alarm_id") Long alarmId,  Authentication authentication) {
		return alarmService.deleteAlarm(alarmId);
	}
}
