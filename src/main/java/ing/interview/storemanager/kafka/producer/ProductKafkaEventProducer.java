package ing.interview.storemanager.kafka.producer;

import ing.interview.storemanager.model.kafka.ProductEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductKafkaEventProducer {

    private KafkaTemplate<String, ProductEvent> kafkaTemplate;

    private static final String TOPIC = "store-manager";

    public void sendEvent(ProductEvent productEvent){
        kafkaTemplate.send(TOPIC, productEvent);
    }

}
