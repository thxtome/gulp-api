package gulp.rest.member.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gulp.rest.member.dto.MemberPrincipal;
import gulp.rest.member.model.BlackToken;
import gulp.rest.member.model.Member;
import gulp.rest.member.repository.BlackTokenRepository;
import gulp.rest.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements UserDetailsService {

	@Autowired
	private final PasswordEncoder passwordEncoder;

	@Autowired
	private final MemberRepository memberRepository;

	@Autowired
	private final BlackTokenRepository blackTokenRepository;

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		Member findMember = memberRepository.findByEmail(memberId);
		return new MemberPrincipal(findMember);
	}

	public ResponseEntity<Object> signin(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		Long pid = memberRepository.saveAndFlush(member).getMemberId();

		HashMap<String, Long> response = new HashMap<>();
		response.put("pid", pid);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<Object> logout(BlackToken token) {
		try {			
			blackTokenRepository.save(token);
		} catch (Exception e) {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	public Boolean checkInvalidToken(BlackToken token) {
			Optional<BlackToken> findTokenOptional = blackTokenRepository.findById(token.getToken());
			return findTokenOptional.map(t -> true).orElse(false);
	}

	public ResponseEntity<Object> emailValidation(String email) {
		Member findMember = memberRepository.findByEmail(email);
		HashMap<String, Boolean> response = new HashMap<>();
		response.put("validation", findMember == null ? true : false);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> deleteMember(Long memberPid) {
		memberRepository.deleteById(memberPid);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	public ResponseEntity<Object> updateMember(Member member) {
		Optional<Member> findMemberOptional = memberRepository.findById(member.getMemberId());
		Member findMember = findMemberOptional.orElseThrow();
		findMember.setGradeId(member.getGradeId());
		findMember.setImgPath(member.getImgPath());
		findMember.setNickname(member.getNickname());
		findMember.setPassword(passwordEncoder.encode(member.getPassword()));
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
 
}
