package paymentservice.dto;



import lombok.Data;

@Data
public class PaymentRequest {
    private String userEmail;
    private String pnrNumber;
    private String amount;
}
