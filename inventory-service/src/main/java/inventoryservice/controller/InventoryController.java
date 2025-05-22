package inventoryservice.controller;



import inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/available-seats")
    public int getAvailableSeats(@RequestParam String trainNumber, @RequestParam String date) {
        return inventoryService.getAvailableSeats(trainNumber, date);
    }

    @PostMapping("/update")
    public String updateSeats(@RequestParam String trainNumber, @RequestParam String date, @RequestParam int bookedSeats) {
        inventoryService.updateSeatAvailability(trainNumber, date, bookedSeats);
        return "Seat inventory updated!";
    }
}
