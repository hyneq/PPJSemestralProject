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
                new City(3071961, "Liberec", countries.get("CZ")),
                new City(3067696, "Praha", countries.get("CZ")),
                new City(5128581, "New York", countries.get("US")),
                new City(2643743, "London", countries.get("UK")),
                new City(2950159, "Berlin", countries.get("DE")),

                // additional
                new City(3074603, "Jablonec nad Nisou", countries.get("CZ")),
                new City(5368361, "Los Angeles", countries.get("US"))
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
                        new City(),
                        new City(null),
                        new City(null, null),
                        new City(null, null, null),
                        new City(null, countries.get("CZ")),
                        new City(null, null, countries.get("CZ")),
                        new City("Unknown",  null),
                        new City(null, "Unknown",  null),
                        new City(123456),
                        new City(123456, null, countries.get("CZ")),
                        new City(123456, null, null)
                )
        );
    }
}
