package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class CityTestData extends NumberIdDataModelTestData<City> {

    @Override
    public City emptyInstance() {
        return new City();
    }

    @Autowired
    public CountryDao countryDao;

    @Override
    public Stream<City> objsValid() {
        return Stream.of(
                new City("Jablonec nad Nisou", countryDao.get(0)),
                new City("Los Angeles", countryDao.getByCode("US"))
        );
    }

    @Override
    public Stream<City> objsInvalid() {
        return Stream.concat(
                super.objsInvalid(),
                Stream.of(
                        new City(null, null),
                        new City(null, countryDao.get(2)),
                        new City("Unknown",  null)
                )
        );
    }
}
