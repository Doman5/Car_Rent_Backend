package pl.domanski.carRent.customer.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.webClient.localization.HereDistanceClient;

@Service
@RequiredArgsConstructor
public class CalculateDistanceService {

    private final HereDistanceClient hereDistanceClient;

    public double calculateDistanceBetweenTwoLocations(String from, String to) {
        String fromCoordinates = hereDistanceClient.getLocationCoordinates(from);
        String toCoordinates = hereDistanceClient.getLocationCoordinates(to);
        return hereDistanceClient.calculateDistance(fromCoordinates, toCoordinates);
    }
}
