package CanditateonboardingSystem.CanditateonboardingSystem.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String JOB_OFFER_QUEUE_NAME = "onboarding";

    @Bean
    public  Queue jobOfferQueue() {
        return new Queue(JOB_OFFER_QUEUE_NAME, true);
    }
}
