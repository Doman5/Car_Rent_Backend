package pl.domanski.carRent.webClient.localization;

import org.springframework.stereotype.Service;

@Service
public class GoogleDistanceCalculator implements DistanceCalculator {
    @Override
    public double calculateDistance(String from, String to) {
        return 0;
    }
}
