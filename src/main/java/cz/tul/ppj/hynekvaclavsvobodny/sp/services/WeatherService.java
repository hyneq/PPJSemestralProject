package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CityRepository;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WeatherService {

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    OpenWeatherMapApiService openWeatherMapApiService;

    @Transactional
    public List<Measurement> updateMeasurements(City city) {
        measurementRepository.deleteAllByIdCity(city);

        Instant now = Instant.now();
        Instant weekAgo = now.minus(7, ChronoUnit.DAYS);
        Instant twoWeeksAgo = weekAgo.minus(7, ChronoUnit.DAYS);

        List<Measurement> measurements = Stream.concat(
                openWeatherMapApiService.getMeasurements(city, twoWeeksAgo, weekAgo).stream(),
                openWeatherMapApiService.getMeasurements(city, weekAgo, now).stream()
        ).collect(Collectors.toList());

        measurementRepository.saveAll(measurements);

        return measurements;
    }

    @Transactional
    public List<Measurement> updateMeasurements(int cityId) {
        return updateMeasurements(cityRepository.getById(cityId));
    }

    @Transactional
    public List<Measurement> updateMeasurements(String cityName) {
        return updateMeasurements(cityRepository.getByName(cityName));
    }

}
