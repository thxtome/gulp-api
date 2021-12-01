package gulp.rest.alarm.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gulp.rest.alarm.repository.AlarmRepository;
import gulp.rest.member.model.Member;
import gulp.rest.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmService {
	
	@Autowired
	private final AlarmRepository alarmRepository;
	
}
