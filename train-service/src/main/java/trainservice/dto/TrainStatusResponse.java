package trainservice.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainStatusResponse {
    private String trainNumber;
    private String trainStatus;
    private String message;
}
