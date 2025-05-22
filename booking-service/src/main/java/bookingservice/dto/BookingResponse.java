package bookingservice.dto;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingResponse {
    private String pnrNumber;
    private String trainNumber;
    private String classType;
    private int numberOfSeats;
}
