package gulp.rest.member.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import gulp.rest.member.model.Member;
import lombok.Data;

@Data
public class MemberPrincipal  implements UserDetails {
	private static final long serialVersionUID = 295874931921099560L;
	private Long memberId;
	private String email;
	private String password;
	private String nickname;
	private String imgPath;
	private Long gradePid;
	
	public MemberPrincipal(Member member) {
		super();
		this.memberId = member.getId();
		this.email = member.getEmail();
		this.password = member.getPassword();
		this.nickname = member.getNickname();
		this.imgPath = member.getImgPath();
		this.gradePid = member.getGradeId();
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	public String getUserNickName() {
		return this.nickname;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public static MemberPrincipal create(Member member) {
		return new MemberPrincipal(member);
	}
	
}
