package gulp.rest.exception.dto;

import gulp.rest.exception.enums.ServiceError;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -7165609979529099309L;

    private final ServiceError serviceError;

    public ServiceException(ServiceError serviceError) {
        this.serviceError = serviceError;
    }

    public ServiceException(ServiceError serviceError, String message) {
        serviceError.setMessage(message);
        this.serviceError = serviceError;
    }

    public ServiceException(ServiceError serviceError, String message, Throwable cause) {
        super(message, cause);
        this.serviceError = serviceError;
    }

    public ServiceException(ServiceError serviceError, Throwable cause) {
        super(cause);
        this.serviceError = serviceError;
    }

    public ServiceError getServiceError() {
        return serviceError;
    }
}
