package pl.domanski.carRent.webClient.localization;

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

    @Bean
    @ConditionalOnProperty(name="app.localization.distance.calculator", havingValue = "google")
    public DistanceCalculator googleDistanceCalculator() {
        return new GoogleDistanceCalculator();
    }
}
