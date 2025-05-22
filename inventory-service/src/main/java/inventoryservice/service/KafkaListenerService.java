package inventoryservice.service;



import inventoryservice.model.SeatInventory;
import inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;




import org.apache.kafka.clients.consumer.ConsumerRecord;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaListenerService {

    @Autowired
    private InventoryRepository inventoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(KafkaListenerService.class);

    @KafkaListener(topics = "booking-events", groupId = "inventory-group")
    public void consumeBookingEvent(ConsumerRecord<String, String> record) {
        try {
            String message = record.value();
            String[] data = message.split(":");
            if (data.length != 3) {
                logger.error("Invalid message format: {}", message);
                return;
            }

            String trainNumber = data[0];
            String date = data[1];
            int bookedSeats = Integer.parseInt(data[2]);

            Optional<SeatInventory> inventoryOpt = inventoryRepository.findByTrainNumberAndDate(trainNumber, date);
            if (inventoryOpt.isPresent()) {
                SeatInventory inventory = inventoryOpt.get();
                if (inventory.getAvailableSeats() >= bookedSeats) {
                    inventory.setAvailableSeats(inventory.getAvailableSeats() - bookedSeats);
                    inventoryRepository.save(inventory);
                    logger.info("Updated inventory for Train: {} on Date: {}", trainNumber, date);
                } else {
                    logger.warn("Not enough seats available for Train: {} on Date: {}", trainNumber, date);
                }
            } else {
                logger.warn("Inventory not found for Train: {} on Date: {}", trainNumber, date);
            }

        } catch (Exception e) {
            logger.error("Error processing Kafka message: ", e);
        }
    }
}
