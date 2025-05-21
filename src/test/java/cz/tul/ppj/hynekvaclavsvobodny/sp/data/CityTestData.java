package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class CityTestData extends NumberIdDataModelTestData<City> {

    private final CountryTestData countryTestData;

    @Autowired
    public CityTestData(CountryTestData countryTestData) {
        super();
        this.countryTestData = countryTestData;
        addDependency(countryTestData);
    }

    @Override
    public City emptyInstance() {
        return new City();
    }

    @Override
    public Stream<City> objsValid() {
        Map<String, Country> countries = countryTestData.getObjsValidByCode();

        return Stream.of(
                // same as in data.sql
                new City("Liberec", countries.get("CZ")),
                new City("Praha", countries.get("CZ")),
                new City("New York", countries.get("US")),
                new City("London", countries.get("UK")),
                new City("Berlin", countries.get("DE")),

                // additional
                new City("Jablonec nad Nisou", countries.get("CZ")),
                new City("Los Angeles", countries.get("US"))
        );
    }

    public Map<String, City> getObjsValidByName() {
        return TestDataUtils.mapByKey(getObjsValid(), City::getName);
    }

    @Override
    public Stream<City> objsInvalid() {
        Map<String, Country> countries = countryTestData.getObjsValidByCode();

        return Stream.concat(
                super.objsInvalid(),
                Stream.of(
                        new City(null, null),
                        new City(null, countries.get("CZ")),
                        new City("Unknown",  null)
                )
        );
    }
}
