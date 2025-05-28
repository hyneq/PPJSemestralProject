package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {

    @Autowired
    MeasurementCrudDelegate crudDelegate;

    @Autowired
    MeasurementService service;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Measurement>> getAll() {
        return crudDelegate.getAll();
    }

    @GetMapping("/id/{cityId}/{datetime}")
    public ResponseEntity<Measurement> getById(@PathVariable int cityId, @PathVariable Instant datetime) {
        return ResponseEntity.of(service.getById(cityId, datetime));
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody Measurement obj) {
        return crudDelegate.create(obj);
    }

    @PatchMapping("/id/{cityId}/{datetime}")
    public ResponseEntity<?> update(@RequestBody Measurement obj, @PathVariable Integer cityId, @PathVariable Instant datetime) {
        service.update(obj);
        return ResponseEntity.ok(obj.getId());
    }

    @DeleteMapping("/id/{cityId}/{datetime}")
    public ResponseEntity<?> delete(@PathVariable Integer cityId, @PathVariable Instant datetime) {
        service.deleteById(cityId, datetime);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/latest/city_id/{cityId}")
    public ResponseEntity<Measurement> getLatestMeasurementByCityId(@PathVariable Integer cityId) {
        return ResponseEntity.of(service.getLatestMeasurementByCityId(cityId));
    }

    @GetMapping("/latest/city_name/{cityName}")
    public ResponseEntity<Measurement> getLatestMeasurementByCityName(@PathVariable String cityName) {
        return ResponseEntity.of(service.getLatestMeasurementByCityName(cityName));
    }

    @GetMapping("/last_day/list/city_id/{cityId}")
    public ResponseEntity<Iterable<Measurement>> getLastDayMeasurementsByCityId(@PathVariable Integer cityId) {
        return ResponseEntity.of(service.getLastDayMeasurementsByCityId(cityId));
    }

    @GetMapping("/last_day/list/city_name/{cityName}")
    public ResponseEntity<Iterable<Measurement>> getLastDayMeasurementsByCityName(@PathVariable String cityName) {
        return ResponseEntity.of(service.getLastDayMeasurementsByCityName(cityName));
    }

    @GetMapping("/last_day/avg/city_id/{cityId}")
    public ResponseEntity<MeasurementAggregation> getLastDayAverageByCityId(@PathVariable Integer cityId) {
        return ResponseEntity.of(service.getLastDayAverageByCityId(cityId));
    }

    @GetMapping("/last_day/avg/city_name/{cityName}")
    public ResponseEntity<MeasurementAggregation> getLastDayAverageByCityName(@PathVariable String cityName) {
        return ResponseEntity.of(service.getLastDayAverageByCityName(cityName));
    }

    @GetMapping("/last_week/list/city_id/{cityId}")
    public ResponseEntity<Iterable<Measurement>> getLastWeekMeasurementsByCityId(@PathVariable Integer cityId) {
        return ResponseEntity.of(service.getLastWeekMeasurementsByCityId(cityId));
    }

    @GetMapping("/last_week/list/city_name/{cityName}")
    public ResponseEntity<Iterable<Measurement>> getLastWeekMeasurementsByCityName(@PathVariable String cityName) {
        return ResponseEntity.of(service.getLastWeekMeasurementsByCityName(cityName));
    }

    @GetMapping("/last_week/avg/city_id/{cityId}")
    public ResponseEntity<MeasurementAggregation> getLastWeekAverageByCityId(@PathVariable Integer cityId) {
        return ResponseEntity.of(service.getLastWeekAverageByCityId(cityId));
    }

    @GetMapping("/last_week/avg/city_name/{cityName}")
    public ResponseEntity<MeasurementAggregation> getLastWeekAverageByCityName(@PathVariable String cityName) {
        return ResponseEntity.of(service.getLastWeekAverageByCityName(cityName));
    }

    @GetMapping("/last_two_weeks/list/city_id/{cityId}")
    public ResponseEntity<Iterable<Measurement>> getLastTwoWeeksMeasurementsByCityId(@PathVariable Integer cityId) {
        return ResponseEntity.of(service.getLastTwoWeeksMeasurementsByCityId(cityId));
    }

    @GetMapping("/last_two_weeks/list/city_name/{cityName}")
    public ResponseEntity<Iterable<Measurement>> getLastTwoWeeksMeasurementsByCityName(@PathVariable String cityName) {
        return ResponseEntity.of(service.getLastTwoWeeksMeasurementsByCityName(cityName));
    }

    @GetMapping("/last_two_weeks/avg/city_id/{cityId}")
    public ResponseEntity<MeasurementAggregation> getLastTwoWeeksAverageByCityId(@PathVariable Integer cityId) {
        return ResponseEntity.of(service.getLastTwoWeeksAverageByCityId(cityId));
    }

    @GetMapping("/last_two_weeks/avg/city_name/{cityName}")
    public ResponseEntity<MeasurementAggregation> getLastTwoWeeksAverageByCityName(@PathVariable String cityName) {
        return ResponseEntity.of(service.getLastTwoWeeksAverageByCityName(cityName));
    }


}
