package inventoryservice.model;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SeatInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;
    private String date;
    private int totalSeats;
    private int availableSeats;
}
