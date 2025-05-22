package trainservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import trainservice.client.TrainApiClient;
import trainservice.dto.TrainStatusResponse;
import trainservice.model.TrainBetweenStationsResponse;
import trainservice.dto.TrainServiceException;

@Slf4j
@Service
public class TrainService {

    private final TrainApiClient trainApiClient;
    private final String apiKey;
    private final String apiHost;

    @Autowired
    public TrainService(TrainApiClient trainApiClient,
                        @Value("${railway.api.key}") String apiKey,
                        @Value("${railway.api.host}") String apiHost) {
        this.trainApiClient = trainApiClient;
        this.apiKey = apiKey;
        this.apiHost = apiHost;
    }

    public TrainBetweenStationsResponse getTrainsBetweenStations(String from, String to, String date) {
        try {
            log.info("Fetching trains from {} to {} on {}", from, to, date);
            TrainBetweenStationsResponse response = (TrainBetweenStationsResponse) trainApiClient.getTrainsBetweenStations(
                    apiKey,
                    apiHost,
                    from,
                    to,
                    date
            );

            if (!response.isStatus()) {
                throw new TrainServiceException("Failed to fetch train data: " + response.getMessage());
            }

            log.debug("Successfully fetched {} trains", response.getData().size());
            return response;
        } catch (Exception e) {
            log.error("Error fetching trains between stations", e);
            throw new TrainServiceException("Service unavailable. Please try again later.", e);
        }
    }

    public TrainStatusResponse checkSeatAvailability(String trainNumber, String date) {
        // Query database or external API
        return new TrainStatusResponse(trainNumber, availableSeats);
    }
}