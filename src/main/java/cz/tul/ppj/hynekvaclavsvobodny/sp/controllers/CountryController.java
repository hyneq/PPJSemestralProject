package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
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
    public ResponseEntity<Country> getById(@PathVariable String id) {
        return crudDelegate.getById(id);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Country> getByCode(@PathVariable String code) {
        return ResponseEntity.of(service.getByCode(code));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Country> getByName(@PathVariable String name) {
        return ResponseEntity.of(service.getByName(name));
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody Country obj) {
        return crudDelegate.create(obj);
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> update(@RequestBody Country obj, @PathVariable String id) {
        return crudDelegate.update(obj, id);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return crudDelegate.delete(id);
    }

}
