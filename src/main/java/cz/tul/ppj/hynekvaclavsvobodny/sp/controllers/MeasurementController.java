package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.MeasurementRepository;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

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

    @GetMapping("/id/{city_id}/{datetime}")
    public ResponseEntity<Measurement> getById(@PathVariable int cityId, @PathVariable Instant datetime) {
        return ResponseEntity.of(service.getById(cityId, datetime));
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody Measurement obj) {
        return crudDelegate.create(obj);
    }

    @PatchMapping("/id/{city_id}/{datetime}")
    public ResponseEntity<?> update(@RequestBody Measurement obj, @RequestParam Integer cityId, @RequestParam Instant datetime) {
        service.update(obj);
        return ResponseEntity.ok(obj.getId());
    }

    @DeleteMapping("/id/{city_id}/{datetime}")
    public ResponseEntity<?> delete(@RequestParam Integer cityId, @RequestParam Instant datetime) {
        service.deleteById(cityId, datetime);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/latest/city_id/{city_id}")
    public ResponseEntity<Measurement> getLatestMeasurementByCityId(@RequestParam Integer cityId) {
        return ResponseEntity.of(service.getLatestMeasurementByCityId(cityId));
    }

    @GetMapping("/latest/city_name/{city_name}")
    public ResponseEntity<Measurement> getLatestMeasurementByCityName(@RequestParam String cityName) {
        return ResponseEntity.of(service.getLatestMeasurementByCityName(cityName));
    }

    @GetMapping("/last_day/list/city_id/{city_id}")
    public ResponseEntity<List<Measurement>> getLastDayMeasurementsByCityId(@RequestParam Integer cityId) {
        return ResponseEntity.of(service.getLastDayMeasurementsByCityId(cityId));
    }

    @GetMapping("/last_day/list/city_name/{city_name}")
    public ResponseEntity<List<Measurement>> getLastDayMeasurementsByCityName(@RequestParam String cityName) {
        return ResponseEntity.of(service.getLastDayMeasurementsByCityName(cityName));
    }

    @GetMapping("/last_day/avg/city_id/{city_id}")
    public ResponseEntity<MeasurementAggregation> getLastDayAverageByCityId(@RequestParam Integer cityId) {
        return ResponseEntity.of(service.getLastDayAverageByCityId(cityId));
    }

    @GetMapping("/last_day/avg/city_name/{city_name}")
    public ResponseEntity<MeasurementAggregation> getLastDayAverageByCityName(@RequestParam String cityName) {
        return ResponseEntity.of(service.getLastDayAverageByCityName(cityName));
    }

    @GetMapping("/last_week/list/city_id/{city_id}")
    public ResponseEntity<List<Measurement>> getLastWeekMeasurementsByCityId(@RequestParam Integer cityId) {
        return ResponseEntity.of(service.getLastWeekMeasurementsByCityId(cityId));
    }

    @GetMapping("/last_week/list/city_name/{city_name}")
    public ResponseEntity<List<Measurement>> getLastWeekMeasurementsByCityName(@RequestParam String cityName) {
        return ResponseEntity.of(service.getLastWeekMeasurementsByCityName(cityName));
    }

    @GetMapping("/last_week/avg/city_id/{city_id}")
    public ResponseEntity<MeasurementAggregation> getLastWeekAverageByCityId(@RequestParam Integer cityId) {
        return ResponseEntity.of(service.getLastWeekAverageByCityId(cityId));
    }

    @GetMapping("/last_week/avg/city_name/{city_name}")
    public ResponseEntity<MeasurementAggregation> getLastWeekAverageByCityName(@RequestParam String cityName) {
        return ResponseEntity.of(service.getLastWeekAverageByCityName(cityName));
    }

    @GetMapping("/last_two_weeks/list/city_id/{city_id}")
    public ResponseEntity<List<Measurement>> getLastTwoWeeksMeasurementsByCityId(@RequestParam Integer cityId) {
        return ResponseEntity.of(service.getLastTwoWeeksMeasurementsByCityId(cityId));
    }

    @GetMapping("/last_two_weeks/list/city_name/{city_name}")
    public ResponseEntity<List<Measurement>> getLastTwoWeeksMeasurementsByCityName(@RequestParam String cityName) {
        return ResponseEntity.of(service.getLastTwoWeeksMeasurementsByCityName(cityName));
    }

    @GetMapping("/last_two_weeks/avg/city_id/{city_id}")
    public ResponseEntity<MeasurementAggregation> getLastTwoWeeksAverageByCityId(@RequestParam Integer cityId) {
        return ResponseEntity.of(service.getLastTwoWeeksAverageByCityId(cityId));
    }

    @GetMapping("/last_two_weeks/avg/city_name/{city_name}")
    public ResponseEntity<MeasurementAggregation> getLastTwoWeeksAverageByCityName(@RequestParam String cityName) {
        return ResponseEntity.of(service.getLastTwoWeeksAverageByCityName(cityName));
    }


}
