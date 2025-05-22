package trainservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrainSchedule {
    private String trainNumber;
    private String trainName;
    private String departureTime;
    private String arrivalTime;
    private String travelTime;
    private List<String> runningDays;
}