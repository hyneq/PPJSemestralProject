package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class CityTestData extends NumberIdDataModelTestData<City> {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public City emptyInstance() {
        return new City();
    }

    @Override
    public Stream<City> objsValid() {
        return Stream.of(
                new City("Jablonec nad Nisou", countryRepository.getById(1)),
                new City("Los Angeles", countryRepository.getByCode("US"))
        );
    }

    @Override
    public Stream<City> objsInvalid() {
        return Stream.concat(
                super.objsInvalid(),
                Stream.of(
                        new City(null, null),
                        new City(null, countryRepository.getById(2)),
                        new City("Unknown",  null)
                )
        );
    }
}
