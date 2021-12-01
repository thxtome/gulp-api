package gulp.rest.member;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gulp.rest.member.model.Member;
import gulp.rest.member.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/email-validation")
	@ApiOperation(value = "이메일 중복 확인", notes = "사용자의 이메일을 받아서 중복확인을 진행한다.")
	@ApiImplicitParam(
			name = "email", 
			value = "sample\n\n " + "test@test.com",
			required = true, 
			dataTypeClass = String.class)
	public ResponseEntity<Object> emailValidation(String email) {
		return memberService.emailValidation(email);
	}
	
	@PostMapping("/signup")
	@ApiOperation(value = "회원가입", notes = "사용자의 정보를 받아서 회원가입을 진행한다.")
	@ApiImplicitParam(
		name = "member", 
		value = "sample\n\n " + "{\"email\":\"test@test.com\", \"password\\\":\"test\", \"nickname\":\"jpark\"}",
		required = true, 
		dataTypeClass = Member.class, 
		paramType = "body")
	
	public ResponseEntity<Object> createMember(@RequestBody Member member) {
		return memberService.signin(member);
	}

	@ApiOperation(value = "로그인", notes = "이메일과 비밀번호를 받아서 로그인을 진행한다.")
	@PostMapping("/signin")
	@ApiImplicitParam(
			name = "member", 
			value = "sample\n\n " + "{\"email\":\"test@test.com\", \"password\\\":\"test\"}",
			required = true, 
			dataTypeClass = Member.class, 
			paramType = "body")
	public void fakeLogin(@RequestBody Member member) {
		throw new IllegalStateException(
				"This method shouldn't be called. It's implemented by Spring Security filters.");
	}

	@ApiOperation(value = "로그아웃", notes = "사용자를 로그아웃 시키고 토큰을 무효화 시킨다.")
	@PostMapping("/logout")
	public void fakeLogout() {
		throw new IllegalStateException(
				"This method shouldn't be called. It's implemented by Spring Security filters.");
	}
	
	@PutMapping("/member")
	@ApiOperation(value = "회원 정보 변경", notes = "사용자의 정보를 받아서 회원정보 변경을 진행한다.")
	@ApiImplicitParam(
		name = "member", 
		value = "sample\n\n " + "{\"password\":\"test123\", \"nickname\":\"jpark\",\"imgPath\":\"https://firebasestorage.googleapis.com/v0/b/medicine-cc1f6.appspot.com/o/face.png?alt=media\"}",
		required = true, 
		dataTypeClass = Member.class, 
		paramType = "body")
	
	public ResponseEntity<Object> updateMember(@RequestBody Member member, Authentication authentication) {
		member.setMemberId((Long)authentication.getPrincipal());
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member")
	@ApiOperation(value = "회원 탈퇴", notes = "사용자의 정보를 받아서 탈퇴를 진행한다.")
	public ResponseEntity<Object> deleteMember(Authentication authentication) {
		return memberService.deleteMember((Long)authentication.getPrincipal());
	}

}
