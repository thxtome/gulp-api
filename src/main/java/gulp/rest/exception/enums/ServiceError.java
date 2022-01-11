package gulp.rest.exception.enums;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum ServiceError {

    DEFAULT_ERROR(){
        @Override
        public ResponseEntity<Object> getResponse() {
            String message = getMessage();
            HashMap<String, String> hm = new HashMap<>();
            hm.put("error_code", "E000");
            hm.put("message", "일시적인 오류가 발생했습니다.");
            return new ResponseEntity<Object>(hm, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    },
    
    DATA_ERROR(){
        @Override
        public ResponseEntity<Object> getResponse() {
            String message = getMessage();
            HashMap<String, String> hm = new HashMap<>();
            hm.put("error_code", "E001");
            hm.put("message", "데이터 오류가 발생했습니다.");
            return new ResponseEntity<Object>(hm, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    },
    
    NO_SUCH_SESSION(){
        @Override
        public ResponseEntity<Object> getResponse() {
            String message = getMessage();
            HashMap<String, String> hm = new HashMap<>();
            hm.put("error_code", "E100");
            hm.put("message", "로그인 정보를 확인해주세요.");
            return new ResponseEntity<Object>(hm, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    },
    
    HAS_NOT_TOKEN(){
        @Override
        public ResponseEntity<Object> getResponse() {
            String message = getMessage();
            HashMap<String, String> hm = new HashMap<>();
            hm.put("error_code", "E101");
            hm.put("message", "잘못된 접근입니다. 로그인을 확인해주세요.");
            return new ResponseEntity<Object>(hm, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    },    
    
    NO_SUCH_USER(){
        @Override
        public ResponseEntity<Object> getResponse() {
            String message = getMessage();
            HashMap<String, String> hm = new HashMap<>();
            hm.put("error_code", "E102");
            hm.put("message", "아이디, 비밀번호를 확인해주세요.");
            return new ResponseEntity<Object>(hm, HttpStatus.UNAUTHORIZED);
        }
    },

    NO_HAS_ROLE(){
        @Override
        public ResponseEntity<Object> getResponse() {
            String message = getMessage();
            HashMap<String, String> hm = new HashMap<>();
            hm.put("error_code", "E103");
            hm.put("message", "권한을 확인해주세요.");
            return new ResponseEntity<Object>(hm, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    },
	
    INVALID_SESSION(){
        @Override
        public ResponseEntity<Object> getResponse() {
            String message = getMessage();
            HashMap<String, String> hm = new HashMap<>();
            hm.put("error_code", "E104");
            hm.put("message", "잘못된 세션입니다.");
            return new ResponseEntity<Object>(hm, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    },
    
    DUPLICATE_USER_ID(){
        @Override
            public ResponseEntity<Object> getResponse() {
                String message = getMessage();
                HashMap<String, String> hm = new HashMap<>();
                hm.put("error_code", "E105");
                hm.put("message", "이미 사용중인 아이디입니다.");
                return new ResponseEntity<Object>(hm, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    },    

    
    INVALID_PASSWORD(){
        @Override
        public ResponseEntity<Object> getResponse() {
            String message = getMessage();
            HashMap<String, String> hm = new HashMap<>();
            hm.put("error_code", "E106");
            hm.put("message", "비밀번호를 확인해주세요.");
            return new ResponseEntity<Object>(hm, HttpStatus.UNAUTHORIZED);
        }
    },
    
    RESOURCE_NOT_FOUND(){
    	@Override
    	public ResponseEntity<Object> getResponse() {
    		HashMap<String, String> hm = new HashMap<>();
    		hm.put("error_code", "E404");
    		hm.put("message", Optional.ofNullable(getMessage()).orElse("데이터가 존재하지 않습니다."));
    		return new ResponseEntity<Object>(hm, HttpStatus.NOT_FOUND);
    	}
    },
    
    ALREADY_EXISTS(){
    	@Override
    	public ResponseEntity<Object> getResponse() {
    		HashMap<String, String> hm = new HashMap<>();
    		hm.put("error_code", "E409");
    		hm.put("message", Optional.ofNullable(getMessage()).orElse("이미 존재하는 데이터입니다."));
    		return new ResponseEntity<Object>(hm, HttpStatus.CONFLICT);
    	}
    },
    
 
    INVALID_PARAMETER(){
        @Override
        public ResponseEntity<Object> getResponse() {
            String message = getMessage();
            HashMap<String, String> hm = new HashMap<>();
            hm.put("error_code", "E400");
            hm.put("message", "필수값을 확인해주세요.");
            return new ResponseEntity<Object>(hm, HttpStatus.BAD_REQUEST);
        }
    };	
	
    private String message;

    public abstract ResponseEntity<Object> getResponse();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}