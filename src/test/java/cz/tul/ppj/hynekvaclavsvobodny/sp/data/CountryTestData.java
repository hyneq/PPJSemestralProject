package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class CountryTestData extends DataModelTestData<Country, String> {

    @Override
    public Country emptyInstance() {
        return new Country();
    }

    @Override
    public Stream<Country> objsValid() {
        return Stream.of(
                // same as in data.sql
                new Country("CZ", "Czech Republic"),
                new Country("US", "United States"),
                new Country("UK", "United Kingdom"),
                new Country("DE", "Germany"),

                // additional
                new Country("AT", "Austria"),
                new Country("DK", "Denmark")
        );
    }

    public Map<String,Country> getObjsValidByCode() {
        return TestDataUtils.mapByKey(getObjsValid(), Country::getCode);
    }

    @Override
    public Stream<Country> objsInvalid() {
        return Stream.concat(
                super.objsInvalid(),
                Stream.of(
                        new Country(null, null),
                        new Country("CZ", null),
                        new Country(null, "Germany")
                )
        );
    }

    @Override
    public Stream<String> idsValid() {
        return codesValid();
    }

    @Override
    public Stream<String> idsInvalid() {
        return codesInvalid();
    }

    private Stream<String> codesValid() {
        return Stream.of(null, "CZ", "UK", "US", "FR", "DE");
    }

    private Stream<String> codesInvalid() {
        return Stream.of("", "cz", "fr", "de", "Czechia", "Cz", "cZ", "ukraIne");
    }
}
