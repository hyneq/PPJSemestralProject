package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CityRepository;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    public void createCountry(Country country) {
        countryRepository.save(country);
    }

    public void createCity(City city) {
        cityRepository.save(city);
    }

}
