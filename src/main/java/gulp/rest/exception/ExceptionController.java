package gulp.rest.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import gulp.rest.exception.dto.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(ServiceException.class)
	  public ResponseEntity<Object> responseException(ServiceException e){
		return e.getServiceError().getResponse();
	  }
	
}
