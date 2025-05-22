package bookingservice.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    @NotBlank
    private String trainNumber;
    private String classType;
    @Min(1)
    private int numberOfSeats;
    @Email
    private String userEmail;
}
