package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class CountryTestData extends NumberIdDataModelTestData<Country> {

    @Override
    public Country emptyInstance() {
        return new Country();
    }

    @Override
    public Stream<Country> objsValid() {
        return Stream.of(
                new Country("AT", "Austria"),
                new Country("DK", "Denmark")
        );
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
}
