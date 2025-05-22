package trainservice.dto;

public class TrainServiceException extends RuntimeException {
    public TrainServiceException(String message) {
        super(message);
    }

    public TrainServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}