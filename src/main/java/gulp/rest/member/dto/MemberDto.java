package gulp.rest.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDto {
	private Long id;
	private String email;
	private String password;
	private String nickname;
	private String img_path;
	private Long grade_pid;
	
}
