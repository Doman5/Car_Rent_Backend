package pl.domanski.carrent.webClient.localization;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DistanceCalculatorConfig {

    @Bean
    @ConditionalOnProperty(name="app.localization.distance.calculator", matchIfMissing = true, havingValue = "here")
    public DistanceCalculator hereDistanceCalculator() {
        return new HereDistanceCalculator();
    }

}
