package pl.domanski.carrent.webclient.localization;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.domanski.carrent.webClient.localization.DistanceCalculator;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DistanceCalculatorService {

    @Value("${app.localization.distance.calculator}")
    private String chosenOption;
    private final Map<String, DistanceCalculator> distanceCalculatorMap;

    public DistanceCalculator getInstance() {
        if(chosenOption.equals("google")) {
            distanceCalculatorMap.get("googleDistanceCalculator");
        }
        return distanceCalculatorMap.get("hereDistanceCalculator");
    }

    public DistanceCalculator getInstance(String beanName) {
        if(chosenOption.equals("here")) {
            return distanceCalculatorMap.get("hereDistanceCalculator");
        }
        return distanceCalculatorMap.get(beanName);
    }
}
