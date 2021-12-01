package gulp.conf.security.jwt;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import gulp.rest.member.model.BlackToken;
import gulp.rest.member.service.MemberService;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{

	MemberService service;
	
	public CustomLogoutSuccessHandler(MemberService service) {
		super();
		this.service = service;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
			
			String token = request.getHeader(JwtProperties.HEADER_STRING);
			
			BlackToken blackToken = new BlackToken();
			blackToken.setToken(token);
			
			service.logout(blackToken);
			
	        //바디를 채워준다.
	        OutputStream out = response.getOutputStream();
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.writeValue(out, new ResponseEntity<>(HttpStatus.OK));
	}

}
