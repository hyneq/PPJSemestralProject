package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CityService extends NumberIdDataModelService<CityRepository, City> {

    @Autowired
    CountryService countryService;

    public CityService() {
        super("modelName");
    }

    public City getByName(String name) {
        return repository.getByName(name);
    }

    public List<City> getByCountry(Country country) {
        return repository.getByCountry(country);
    }

    public List<City> getByCountryCode(String countryCode) {
        Country country = countryService.getByCode(countryCode);
        if (country == null) {
            return null;
        }
        return getByCountry(country);
    }

    public List<City> getByCountryName(String countryName) {
        Country country = countryService.getByName(countryName);
        if (country == null) {
            return null;
        }
        return getByCountry(country);
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    public void deleteByCountry(Country country) {
        repository.deleteByCountry(country);
    }

    public void deleteByCountryCode(String countryCode) {
        Country country = countryService.getByCode(countryCode);
        if (country == null) {
            return;
        }

        deleteByCountry(country);
    }
    public void deleteByCountryName(String countryName) {
        Country country = countryService.getByName(countryName);
        if (country == null) {
            return;
        }

        deleteByCountry(country);
    }
}
