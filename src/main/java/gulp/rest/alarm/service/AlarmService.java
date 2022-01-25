package gulp.rest.alarm.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gulp.rest.alarm.dto.AlarmForm;
import gulp.rest.alarm.dto.AlarmHistDto;
import gulp.rest.alarm.model.Alarm;
import gulp.rest.alarm.model.AlarmHist;
import gulp.rest.alarm.repository.AlarmHistRepository;
import gulp.rest.alarm.repository.AlarmRepository;
import gulp.rest.exception.dto.ServiceException;
import gulp.rest.exception.enums.ServiceError;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmService {

	private final AlarmRepository alarmRepository;
	
	private final AlarmHistRepository alarmHistRepository;

	public ResponseEntity<Object> createAlarm(AlarmForm alarmForm, Long memberId) {
		Alarm savedAlarm = alarmRepository.save(new Alarm().create(alarmForm, memberId));
		return new ResponseEntity<Object>(savedAlarm.getId(), HttpStatus.OK);
	}

	public ResponseEntity<Object> getAlarmList(Long memberId, String day) {
		return new ResponseEntity<Object>(alarmRepository.findAllDtoByMemberIdAndDayContains(memberId, day),
				HttpStatus.OK);
	}

	public ResponseEntity<Object> getAlarm(Long memberId, Long alarmId) {
		return new ResponseEntity<Object>(alarmRepository.findDtoByIdAndMemberId(memberId, alarmId), HttpStatus.OK);
	}

	public ResponseEntity<Object> updateAlarm(AlarmForm alarmForm) {
		Alarm findAlarm = alarmRepository.findById(alarmForm.getId()).orElseThrow();
		findAlarm.update(alarmForm);
		return new ResponseEntity<Object>(findAlarm.getId(), HttpStatus.OK);
	}

	public ResponseEntity<Object> deleteAlarm(Long alarmId) {
		Alarm findAlarm = alarmRepository.findById(alarmId).orElseThrow();
		findAlarm.delete();
		return new ResponseEntity<Object>(findAlarm.getId(), HttpStatus.OK);
	}
	
	public ResponseEntity<Object> doseMedicines(Long memberId, Long alarmId) {
		LocalDateTime today = LocalDate.now().atTime(0, 0);
		AlarmHist findAlarmHist = alarmHistRepository.findByAlarmIdAndCreatedAtAfter(alarmId, today);
		if(findAlarmHist != null) {
			throw new ServiceException(ServiceError.ALREADY_EXISTS);
		}
		Alarm findAlarm = alarmRepository.findByIdAndMemberId(memberId, alarmId);
		AlarmHist alarmHist = findAlarm.dose();
		alarmHistRepository.save(alarmHist);
		return new ResponseEntity<Object>(alarmHist.getId(), HttpStatus.OK);
	}

	public ResponseEntity<Object> cancelDoseMedicines(Long principal, Long alarmId) {
		LocalDateTime today = LocalDate.now().atTime(0, 0);
		AlarmHist findAlarmHist = alarmHistRepository.findByAlarmIdAndCreatedAtAfter(alarmId, today);
		if(findAlarmHist == null) {
			throw new ServiceException(ServiceError.RESOURCE_NOT_FOUND);
		}
		alarmHistRepository.delete(findAlarmHist);
		Alarm findAlarm = alarmRepository.findById(alarmId).orElseThrow();
		findAlarm.cancelDose();
		return new ResponseEntity<Object>(findAlarmHist.getId(), HttpStatus.OK);
	}
	
	public ResponseEntity<Object> getAlarmHistForCalendar(Long memberId, String date) {
		List<AlarmHistDto> findAlarmHists = alarmHistRepository.findByAlarmIdAndCreatedAt(memberId, date);
		return new ResponseEntity<Object>(findAlarmHists != null ? findAlarmHists : new String[0] , HttpStatus.OK)  ;
	}
}
