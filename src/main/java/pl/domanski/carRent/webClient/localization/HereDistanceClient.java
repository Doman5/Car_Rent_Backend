package pl.domanski.carRent.webClient.localization;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HereDistanceClient {

    @Value("${here.apikey}")
    private String apiKey = "A9bl-TTSmom8iNeJg_dER6UQ_txVB136p6Yg7jdkN9I";

    public double calculateDistance(String fromCoordinates, String toCoordinates) {
        RestTemplate restTemplate = new RestTemplate();
        String responseString = restTemplate.getForObject(
                "https://router.hereapi.com/v8/routes?transportMode=car&origin={from}&destination={to}&return=summary&apikey={apiKey}",
                String.class, fromCoordinates, toCoordinates, apiKey);

        JSONObject json = new JSONObject(responseString);
        double distance = json.getJSONArray("routes")
                .getJSONObject(0)
                .getJSONArray("sections")
                .getJSONObject(0)
                .getJSONObject("summary")
                .getInt("length");

        return distance / 1000;
    }

    public String getLocationCoordinates(String locationName) {
        RestTemplate restTemplate = new RestTemplate();
        String responseString = restTemplate.getForObject(
                "https://geocode.search.hereapi.com/v1/geocode?q={location}&apiKey={key}",
                String.class, locationName, apiKey);

        JSONObject json = new JSONObject(responseString);
        Double latitude = json.getJSONArray("items")
                .getJSONObject(0)
                .getJSONObject("position")
                .getDouble("lat");
        Double longitude = json.getJSONArray("items")
                .getJSONObject(0)
                .getJSONObject("position")
                .getDouble("lng");

        return String.format("%f,%f",latitude,longitude);
    }
}
