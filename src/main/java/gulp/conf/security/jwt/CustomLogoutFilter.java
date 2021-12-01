package gulp.conf.security.jwt;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import gulp.rest.member.service.MemberService;

public class CustomLogoutFilter extends LogoutFilter{

	public CustomLogoutFilter(MemberService service) {
		super(new CustomLogoutSuccessHandler(service), new SecurityContextLogoutHandler());
		setFilterProcessesUrl("/logout");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setFilterProcessesUrl(String filterProcessesUrl) {
		// TODO Auto-generated method stub
		super.setFilterProcessesUrl(filterProcessesUrl);
	}
	
}
