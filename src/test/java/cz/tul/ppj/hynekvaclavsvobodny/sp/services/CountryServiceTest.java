package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.CountryTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CountryRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;

import static cz.tul.ppj.hynekvaclavsvobodny.sp.data.TestDataUtils.assertObjEqual;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CountryServiceTest extends DataModelServiceTest<CountryService, CountryRepository, Country, String, CountryTestData> {

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByCodeEmpty(Country obj) {
        when(repository.findByCode(obj.getCode()))
                .thenReturn(Optional.empty());

        Optional<Country> retrieved = service.getByCode(obj.getCode());

        assertTrue(retrieved.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByCodeObjsValid(Country obj) {
        when(repository.findByCode(obj.getCode()))
                .thenReturn(Optional.of(obj));

        Optional<Country> retrieved = service.getByCode(obj.getCode());

        assertObjEqual(obj, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByNameEmpty(Country obj) {
        when(repository.findByName(obj.getName()))
                .thenReturn(Optional.empty());

        Optional<Country> retrieved = service.getByName(obj.getName());

        assertTrue(retrieved.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByNameObjsValid(Country obj) {
        when(repository.findByName(obj.getName()))
                .thenReturn(Optional.of(obj));

        Optional<Country> retrieved = service.getByName(obj.getName());

        assertObjEqual(obj, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteById(Country obj) {
        doNothing().when(repository).deleteByCode(obj.getCode());

        service.deleteByCode(obj.getCode());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteByName(Country obj) {
        doNothing().when(repository).deleteByName(obj.getName());

        service.deleteByName(obj.getName());
    }
}
