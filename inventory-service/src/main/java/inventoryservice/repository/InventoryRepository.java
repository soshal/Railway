package inventoryservice.repository;



import inventoryservice.model.SeatInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<SeatInventory, Long> {
    Optional<SeatInventory> findByTrainNumberAndDate(String trainNumber, String date);
}
