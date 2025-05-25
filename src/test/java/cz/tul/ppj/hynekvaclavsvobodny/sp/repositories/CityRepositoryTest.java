package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.CityTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static cz.tul.ppj.hynekvaclavsvobodny.sp.data.TestDataUtils.*;

public class CityRepositoryTest extends NumberIdDataModelRepositoryTest<CityRepository, City, CityTestData, CityRepositoryTestHelper> {

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveLoadByName(City obj) {
        repository.save(obj);

        Optional<City> retrieved = repository.findByName(obj.getName());

        assertObjEqual(obj, retrieved);
    }

    private Stream<Arguments> objsValidGroupedByCountry() {
        return mapToArguments(data.getObjsValidGroupedByCountry());
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCountry")
    public void testSaveLoadByCountry(Country country, List<City> objs) {
        repository.saveAll(objs);

        Iterable<City> retrieved = repository.findAllByCountry(country);

        assertObjsEqual(objs, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteByName(City obj) {
        repository.save(obj);

        repository.deleteByName(obj.getName());

        assertTrue(repository.findByName(obj.getName()).isEmpty());
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCountry")
    public void testDeleteAllByCountry(Country country, List<City> objs) {
        repository.saveAll(objs);

        repository.deleteAllByCountry(country);

        assertTrue(isEmptyIterable(repository.findAllByCountry(country)));
    }

}
