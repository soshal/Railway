package paymentservice.model;



import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Payment {
    @Id
    private String paymentId;
    private String userEmail;
    private String pnrNumber;  // Associated booking
    private String amount;
    private String status;  // Success, Failed, Pending
}
