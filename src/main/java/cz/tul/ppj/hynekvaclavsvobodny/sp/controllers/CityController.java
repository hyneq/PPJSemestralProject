package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    CityCrudDelegate crudDelegate;

    @Autowired
    CityService service;

    @GetMapping("/all")
    public ResponseEntity<Iterable<City>> getAll() {
        return crudDelegate.getAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<City> getById(@PathVariable int id) {
        return crudDelegate.getById(id);
    }

    @GetMapping("/country_code/{countryCode}")
    public ResponseEntity<Iterable<City>> getByCountryCode(@PathVariable String countryCode) {
        return ResponseEntity.of(service.getByCountryCode(countryCode));
    }

    @GetMapping("/country_name/{countryName}")
    public ResponseEntity<Iterable<City>> getByCountryName(@PathVariable String countryName) {
        return ResponseEntity.of(service.getByCountryName(countryName));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<City> getByName(@PathVariable String name) {
        return ResponseEntity.of(service.getByName(name));
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody City obj) {
        return crudDelegate.create(obj);
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> update(@RequestBody City obj, @PathVariable Integer id) {
        return crudDelegate.update(obj, id);
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        return crudDelegate.deleteAll();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return crudDelegate.delete(id);
    }

    @DeleteMapping("/country_code/{countryCode}")
    public ResponseEntity<?> deleteByCountryCode(@PathVariable String countryCode) {
        service.deleteByCountryCode(countryCode);
        return ResponseEntity.ok(countryCode);
    }

    @DeleteMapping("/country_name/{countryName}")
    public ResponseEntity<?> deleteByCountryName(@PathVariable String countryName) {
        service.deleteByCountryName(countryName);
        return ResponseEntity.ok(countryName);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<?> deleteByName(@PathVariable String name) {
        service.deleteByName(name);
        return ResponseEntity.ok(name);
    }

}
