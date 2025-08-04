package ing.interview.storemanager.kafka.aop;

import ing.interview.storemanager.dto.product.ProductDto;
import ing.interview.storemanager.kafka.producer.ProductKafkaEventProducer;
import ing.interview.storemanager.model.kafka.ProductEvent;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
@AllArgsConstructor
public class PublishEventAspect {

    private final ProductKafkaEventProducer producer;

    @AfterReturning(
            pointcut = "@annotation(publishEvent)",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, PublishEvent publishEvent, Object result) {
        if(result instanceof ProductDto productDto){
            String type = publishEvent.type();
            Long productId = productDto.getId();
            ProductEvent productEvent = new ProductEvent(type, productId, Instant.now());
            producer.sendEvent(productEvent);
        }
    }

    @After("@annotation(publishEvent)")
    public void afterDelete(JoinPoint joinPoint, PublishEvent publishEvent) {
        Object[] args = joinPoint.getArgs();
        if(args[0] instanceof Long productId){
            ProductEvent productEvent = new ProductEvent(publishEvent.type(), productId, Instant.now());
            producer.sendEvent(productEvent);
        }
    }


}
