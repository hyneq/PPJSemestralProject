package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CityRepository;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    OpenWeatherMapApiService openWeatherMapApiService;

    public List<Measurement> updateMeasurements(City city) {
        measurementRepository.deleteAllByIdCity(city);

        Instant to = Instant.now();
        Instant from = to.minus(2, ChronoUnit.WEEKS);

        List<Measurement> measurements = openWeatherMapApiService.getMeasurements(city, from, to);

        measurementRepository.saveAll(measurements);

        return measurements;
    }

    public List<Measurement> updateMeasurements(int cityId) {
        return updateMeasurements(cityRepository.getById(cityId));
    }

}
