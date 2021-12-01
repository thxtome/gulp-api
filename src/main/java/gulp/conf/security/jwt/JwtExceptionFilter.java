package gulp.conf.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gulp.rest.exception.JwtAuthenticationException;
import gulp.rest.exception.dto.ServiceException;
import gulp.rest.exception.enums.ServiceError;

public class JwtExceptionFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (NullPointerException e) {
			ServiceException se = new ServiceException(ServiceError.INVALID_PARAMETER);
			//ResponseResult errorResponse = new ResponseResult(ie, "Login", "0003", request.getRequestURI());
			response.setContentType("application/json");
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			//response.getWriter().write(convertObjectToJson(errorResponse));
		} catch (JwtAuthenticationException e) {
			//ResponseResult errorResponse = new ResponseResult(e, "Login", "0003", request.getRequestURI());
			response.setContentType("application/json");
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			//response.getWriter().write(convertObjectToJson(errorResponse));
		}
		
	}

	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}