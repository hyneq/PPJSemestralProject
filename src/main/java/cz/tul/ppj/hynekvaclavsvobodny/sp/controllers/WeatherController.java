package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    WeatherService service;

    @PostMapping("/update/city_id/{cityId}")
    public ResponseEntity<List<Measurement>> updateMeasurements(@PathVariable Integer cityId) {
        return ResponseEntity.ofNullable(service.updateMeasurements(cityId));
    }

    @PostMapping("/update/city_name/{cityName}")
    public ResponseEntity<List<Measurement>> updateMeasurements(@PathVariable String cityName) {
        return ResponseEntity.ofNullable(service.updateMeasurements(cityName));
    }
}
