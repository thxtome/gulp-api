package gulp.rest.alarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gulp.rest.alarm.dto.AlarmForm;
import gulp.rest.alarm.model.Alarm;
import gulp.rest.alarm.repository.AlarmHistRepository;
import gulp.rest.alarm.repository.AlarmMedicineRepository;
import gulp.rest.alarm.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmService {

	@Autowired
	private final AlarmRepository alarmRepository;

	@Autowired
	private final AlarmHistRepository alarmHistRepository;

	@Autowired
	private final AlarmMedicineRepository alarmMedicineRepository;

	public ResponseEntity<Object> createAlarm(AlarmForm alarmForm, Long memberId) {
		Alarm savedAlarm = alarmRepository.save(new Alarm().create(alarmForm, memberId));
		return new ResponseEntity<Object>(savedAlarm.getId(), HttpStatus.OK);
	}

	public ResponseEntity<Object> getAlarmList(Long memberId, String day) {
		return new ResponseEntity<Object>(alarmRepository.findAllByMemberIdAndDayContains(memberId, day),
				HttpStatus.OK);
	}

	public ResponseEntity<Object> getAlarm(Long memberId, Long alarmId) {
		return new ResponseEntity<Object>(alarmRepository.findAllByIdAndMemberId(memberId, alarmId), HttpStatus.OK);
	}

	public ResponseEntity<Object> updateAlarm(AlarmForm alarmForm) {
		Alarm findAlarm = alarmRepository.findById(alarmForm.getId()).orElseThrow();
		alarmMedicineRepository.deleteAllInBatch(findAlarm.getAlarmMedicines());
		findAlarm.update(alarmForm);
		return new ResponseEntity<Object>(findAlarm.getId(), HttpStatus.OK);
	}

	public ResponseEntity<Object> deleteAlarm(Long alarmId) {
		Alarm findAlarm = alarmRepository.findById(alarmId).orElseThrow();
		findAlarm.delete();
		return new ResponseEntity<Object>(findAlarm.getId(), HttpStatus.OK);
	}

}
