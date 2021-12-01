package gulp.conf.security.jwt;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import gulp.rest.exception.JwtAuthenticationException;
import gulp.rest.member.model.BlackToken;
import gulp.rest.member.model.Member;
import gulp.rest.member.service.MemberService;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	MemberService service;
	
	final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberService service) {
		super(authenticationManager);
		this.service = service;
	}

	// 토큰 검증
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 헤더추출
		String header = request.getHeader(JwtProperties.HEADER_STRING);

		// jwt인지 확인
		if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		// 헤더가 있고 jwt가 맞으면 유저의 정보를 가져옴
		Authentication authentication = getUsernamePasswordAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		chain.doFilter(request, response);
	}

	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request)
			throws AuthenticationException {
		String token = request.getHeader(JwtProperties.HEADER_STRING);

		if (token == null) {
			return null;
		}

		// 토큰의 유효성체크
		DecodedJWT decodedToken = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes())).build()
				.verify(token.replace(JwtProperties.TOKEN_PREFIX, ""));
		
		Long memberId = decodedToken.getClaim("memberId").asLong();
		
		
		// db에서 블랙리스트 조회 확인
		if (service.checkInvalidToken(new BlackToken(token))) {
			throw new JwtAuthenticationException("token is expired");
		}

		
		// db에서 유저의 정보와 권한을 가져옴
		if ( memberId == null) {
			return null;
		}
		
		
//		Member member = service.findByEmail(memberId);
		
//		// db에서 로그아웃하거나 탈퇴한 회원인지 확인
//		if (member == null) {
//			throw new JwtAuthenticationException("member in this token is not found");
//		}
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(memberId, null, null);
		return auth;

	}

}