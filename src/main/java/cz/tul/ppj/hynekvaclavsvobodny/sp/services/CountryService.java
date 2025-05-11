package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CountryRepository;

public class CountryService extends NumberIdDataModelService<CountryRepository, Country> {

    public CountryService() {
        super("country");
    }

    public Country getByCode(String code) {
        return repository.getByCode(code);
    }

    public Country getByName(String name) {
        return repository.getByName(name);
    }

    public void deleteByCode(String code) {
        repository.deleteByCode(code);
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }
}
