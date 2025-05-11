package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CityService extends NumberIdDataModelService<CityRepository, City> {

    @Autowired
    CountryService countryService;

    public CityService() {
        super("modelName");
    }

    public Optional<City> getByName(String name) {
        return repository.findByName(name);
    }

    public List<City> getByCountry(Country country) {
        return repository.getByCountry(country);
    }

    public Optional<List<City>> getByCountryCode(String countryCode) {
        return countryService.getByCode(countryCode).map(this::getByCountry);
    }

    public Optional<List<City>> getByCountryName(String countryName) {
        return countryService.getByName(countryName).map(this::getByCountry);
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    public void deleteByCountry(Country country) {
        repository.deleteByCountry(country);
    }

    public void deleteByCountryCode(String countryCode) {
        countryService.getByCode(countryCode).ifPresent(this::deleteByCountry);
    }
    public void deleteByCountryName(String countryName) {
        countryService.getByName(countryName).ifPresent(this::deleteByCountry);
    }
}
