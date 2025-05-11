package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CountryRepository;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    CountryCrudDelegate crudDelegate;

    @Autowired
    CountryService service;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Country>> getAll() {
        return crudDelegate.getAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Country> getById(@PathVariable int id) {
        return crudDelegate.getById(id);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Country> getByCode(@PathVariable String countryCode) {
        return ResponseEntity.of(service.getByCode(countryCode));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Country> getByName(@PathVariable String countryName) {
        return ResponseEntity.of(service.getByName(countryName));
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody Country obj) {
        return crudDelegate.create(obj);
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> update(@RequestBody Country obj, @RequestParam Integer id) {
        return crudDelegate.update(obj, id);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@RequestParam Integer id) {
        return crudDelegate.delete(id);
    }

}
