package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.CityTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CityRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static cz.tul.ppj.hynekvaclavsvobodny.sp.data.TestDataUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CityServiceTest extends NumberIdDataModelServiceTest<CityService, CityRepository, City, CityTestData> {

    @MockitoBean
    private CountryService countryService;

    private Stream<Arguments> objsValidByCountry() {
        return mapToArguments(data.objsValidGroupedByCountry());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByNameEmpty(City obj) {
        when(repository.findByName(obj.getName()))
                .thenReturn(Optional.empty());

        Optional<City> retrieved = service.getByName(obj.getName());

        assertTrue(retrieved.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByNameObjsValid(City obj) {
        when(repository.findByName(obj.getName()))
                .thenReturn(Optional.of(obj));

        Optional<City> retrieved = service.getByName(obj.getName());

        assertObjEqual(obj, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryEmpty(Country country, List<City> objs) {
        when(repository.findAllByCountry(country))
                .thenReturn(List.of());

        Iterable<City> retrieved = service.getByCountry(country);

        assertTrue(isEmptyIterable(retrieved));
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryObjsValid(Country country, List<City> objs) {
        when(repository.findAllByCountry(country))
                .thenReturn(objs);

        Iterable<City> retrieved = service.getByCountry(country);

        assertObjsEqual(objs, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryCodeNull(Country country, List<City> objs) {
        when(countryService.getByCode(country.getCode()))
                .thenReturn(Optional.empty());

        Optional<Iterable<City>> retrieved = service.getByCountryCode(country.getCode());

        assertTrue(retrieved.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryCodeEmpty(Country country, List<City> objs) {
        when(repository.findAllByCountry(country))
                .thenReturn(List.of());

        when(countryService.getByCode(country.getCode()))
                .thenReturn(Optional.of(country));

        Optional<Iterable<City>> retrieved = service.getByCountryCode(country.getCode());

        assertTrue(retrieved.isPresent());
        assertTrue(isEmptyIterable(retrieved.get()));
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryCodeObjsValid(Country country, List<City> objs) {
        when(repository.findAllByCountry(country))
                .thenReturn(objs);

        when(countryService.getByCode(country.getCode()))
                .thenReturn(Optional.of(country));

        Optional<Iterable<City>> retrieved = service.getByCountryCode(country.getCode());

        assertTrue(retrieved.isPresent());
        assertObjsEqual(objs, retrieved.get());
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryNameNull(Country country, List<City> objs) {
        when(countryService.getByName(country.getName()))
                .thenReturn(Optional.empty());

        Optional<Iterable<City>> retrieved = service.getByCountryName(country.getName());

        assertTrue(retrieved.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryNameEmpty(Country country, List<City> objs) {
        when(repository.findAllByCountry(country))
                .thenReturn(List.of());

        when(countryService.getByName(country.getName()))
                .thenReturn(Optional.of(country));

        Optional<Iterable<City>> retrieved = service.getByCountryName(country.getName());

        assertTrue(retrieved.isPresent());
        assertTrue(isEmptyIterable(retrieved.get()));
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testGetByCountryNameObjsValid(Country country, List<City> objs) {
        when(repository.findAllByCountry(country))
                .thenReturn(objs);

        when(countryService.getByName(country.getName()))
                .thenReturn(Optional.of(country));

        Optional<Iterable<City>> retrieved = service.getByCountryName(country.getName());

        assertTrue(retrieved.isPresent());
        assertObjsEqual(objs, retrieved.get());
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testDeleteByCountry(Country country, List<City> objs) {
        doNothing().when(repository).deleteAllByCountry(country);

        service.deleteByCountry(country);
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testDeleteByCountryCodeEmpty(Country country, List<City> objs) {
        when(countryService.getByCode(country.getCode()))
                .thenReturn(Optional.empty());

        doNothing().when(repository).deleteAllByCountry(country);

        service.deleteByCountryCode(country.getCode());
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testDeleteByCountryCode(Country country, List<City> objs) {
        when(countryService.getByCode(country.getCode()))
                .thenReturn(Optional.of(country));

        doNothing().when(repository).deleteAllByCountry(country);

        service.deleteByCountryCode(country.getCode());
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testDeleteByCountryNameEmpty(Country country, List<City> objs) {
        when(countryService.getByName(country.getName()))
                .thenReturn(Optional.empty());

        doNothing().when(repository).deleteAllByCountry(country);

        service.deleteByCountryName(country.getName());
    }

    @ParameterizedTest
    @MethodSource("objsValidByCountry")
    public void testDeleteByCountryName(Country country, List<City> objs) {
        when(countryService.getByName(country.getName()))
                .thenReturn(Optional.of(country));

        doNothing().when(repository).deleteAllByCountry(country);

        service.deleteByCountryName(country.getName());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteByName(City obj) {
        doNothing().when(repository).deleteByName(obj.getName());

        service.deleteByName(obj.getName());
    }
}
