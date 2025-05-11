package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CountryRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService extends NumberIdDataModelService<CountryRepository, Country> {

    public CountryService() {
        super("country");
    }

    public Optional<Country> getByCode(String code) {
        return repository.findByCode(code);
    }

    public Optional<Country> getByName(String name) {
        return repository.findByName(name);
    }

    public void deleteByCode(String code) {
        repository.deleteByCode(code);
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }
}
