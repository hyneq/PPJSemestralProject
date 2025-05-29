package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import jakarta.annotation.PostConstruct;
import org.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpenWeatherMapApiService {

    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherMapApiService.class);

    @Value("${openweathermap.base-url}")
    private String baseUrl;

    @Value("${openweathermap.api-key}")
    private String apiKey;

    private WebClient webClient;

    @PostConstruct
    private void getWebClient() {
        webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("x-api-key", apiKey)
                .build();
    }

    public List<Measurement> getMeasurements(City city, Instant from, Instant to) {
        String json = performApiRequest(city.getId(), from.getEpochSecond(), to.getEpochSecond());

        return parseResponse(json, city);
    }

    private String performApiRequest(int cityId, long from, long to) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/history/city")
                            .queryParam("id", cityId)
                            .queryParam("start", from)
                            .queryParam("end", to)
                            .queryParam("units", "metric")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            logger.error("OpenWeatherMapAPI request failed.", e);
            throw new RuntimeException(e);
        }
    }

    private List<Measurement> parseResponse(String response, City city) {
        List<Measurement> measurements = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(response);

            JSONArray list = root.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject jsonMeasurement = list.getJSONObject(i);
                Measurement measurement = new Measurement();

                measurement.setCity(city);
                measurement.setDatetime(Instant.ofEpochSecond(jsonMeasurement.getLong("dt")));

                JSONObject main = jsonMeasurement.getJSONObject("main");
                measurement.setTemp(main.optDoubleObject("temp"));
                measurement.setTempFeelsLike(main.optDoubleObject("feels_like"));
                measurement.setTempMin(main.optDoubleObject("temp_min"));
                measurement.setTempMax(main.optDoubleObject("temp_max"));
                measurement.setPressure(main.optIntegerObject("pressure"));
                measurement.setHumidity(main.optIntegerObject("humidity"));

                JSONObject wind = jsonMeasurement.getJSONObject("wind");
                measurement.setWindSpeed(wind.optDoubleObject("speed"));
                measurement.setWindDirection(wind.optIntegerObject("deg"));
                measurement.setWindGust(wind.optDoubleObject("gust"));

                JSONObject clouds = jsonMeasurement.getJSONObject("clouds");
                measurement.setClouds(clouds.optIntegerObject("all"));

                JSONObject weather = jsonMeasurement.getJSONArray("weather").getJSONObject(0);
                measurement.setConditionId(weather.getInt("id"));

                measurements.add(measurement);
            }
        } catch (Exception e) {
            logger.error("Parsing OpenWeatherMap API response failed", e);
            throw new RuntimeException(e);
        }

        return measurements;
    }

}
