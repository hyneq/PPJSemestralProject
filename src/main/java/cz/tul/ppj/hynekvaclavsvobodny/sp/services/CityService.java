package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService extends NumberIdDataModelService<CityRepository, City> {

    @Autowired
    CountryService countryService;

    public CityService() {
        super("modelName");
    }

    public Optional<City> getByName(String name) {
        return repository.findByName(name);
    }

    public Iterable<City> getByCountry(Country country) {
        return repository.findAllByCountry(country);
    }

    public Optional<Iterable<City>> getByCountryCode(String countryCode) {
        return countryService.getByCode(countryCode).map(this::getByCountry);
    }

    public Optional<Iterable<City>> getByCountryName(String countryName) {
        return countryService.getByName(countryName).map(this::getByCountry);
    }

    public void deleteByCountry(Country country) {
        repository.deleteAllByCountry(country);
    }

    public void deleteByCountryCode(String countryCode) {
        countryService.getByCode(countryCode).ifPresent(this::deleteByCountry);
    }

    public void deleteByCountryName(String countryName) {
        countryService.getByName(countryName).ifPresent(this::deleteByCountry);
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

}
