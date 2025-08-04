package ing.interview.storemanager.model.kafka;


import java.time.Instant;

public record ProductEvent (String type, Long productId, Instant timestamp) {
}
