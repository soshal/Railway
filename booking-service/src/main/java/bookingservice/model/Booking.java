package bookingservice.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

;

@Entity
@Data
public class Booking {

    @Id
    private String pnrNumber;
    private String trainNumber;
    private String classType;
    private int numberOfSeats;
    private String userEmail;
}
