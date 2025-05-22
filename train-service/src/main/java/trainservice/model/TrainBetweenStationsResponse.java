package trainservice.model;



import lombok.Getter;
import lombok.Setter;
import trainservice.dto.TrainSchedule;

import java.util.List;

@Getter
@Setter
public class TrainBetweenStationsResponse {
    private boolean status;
    private String message;
    private List<TrainSchedule> data;
}