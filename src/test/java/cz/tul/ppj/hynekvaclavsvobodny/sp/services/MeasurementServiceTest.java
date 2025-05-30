package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.MeasurementTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.MeasurementRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static cz.tul.ppj.hynekvaclavsvobodny.sp.data.TestDataUtils.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MeasurementServiceTest extends DataModelServiceTest<MeasurementService, MeasurementRepository, Measurement, Measurement.MeasurementId, MeasurementTestData> {

    @MockitoBean
    private CityService cityService;

    private Stream<Arguments> objsValidGroupedByCity() {
        return mapToArguments(data.objsValidGroupedByCity());
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCity")
    public void testGetByCityEmpty(City city, List<Measurement> objs) {
        when(repository.findAllByIdCity(city))
                .thenReturn(List.of());

        Iterable<Measurement> retrieved = service.getByCity(city);

        assertTrue(isEmptyIterable(retrieved));
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCity")
    public void testGetByCityValid(City city, List<Measurement> objs) {
        when(repository.findAllByIdCity(city))
                .thenReturn(objs);

        Iterable<Measurement> retrieved = service.getByCity(city);

        assertObjsEqual(objs, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCity")
    public void testGetByCityIdNull(City city, List<Measurement> objs) {
        when(cityService.getById(city.getId()))
                .thenReturn(Optional.empty());

        Optional<Iterable<Measurement>> retrieved = service.getByCityId(city.getId());

        assertTrue(retrieved.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCity")
    public void testGetByCityIdEmpty(City city, List<Measurement> objs) {
        when(cityService.getById(city.getId()))
                .thenReturn(Optional.of(city));

        when(repository.findAllByIdCity(city))
                .thenReturn(List.of());

        Optional<Iterable<Measurement>> retrieved = service.getByCityId(city.getId());

        assertTrue(retrieved.isPresent());
        assertTrue(isEmptyIterable(retrieved.get()));
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCity")
    public void testGetByCityIdValid(City city, List<Measurement> objs) {
        when(cityService.getById(city.getId()))
                .thenReturn(Optional.of(city));

        when(repository.findAllByIdCity(city))
                .thenReturn(objs);

        Optional<Iterable<Measurement>> retrieved = service.getByCityId(city.getId());

        assertTrue(retrieved.isPresent());
        assertObjsEqual(objs, retrieved.get());
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCity")
    public void testGetByCityNameNull(City city, List<Measurement> objs) {
        when(cityService.getByName(city.getName()))
                .thenReturn(Optional.empty());

        Optional<Iterable<Measurement>> retrieved = service.getByCityName(city.getName());

        assertTrue(retrieved.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCity")
    public void testGetByCityNameEmpty(City city, List<Measurement> objs) {
        when(cityService.getByName(city.getName()))
                .thenReturn(Optional.of(city));

        when(repository.findAllByIdCity(city))
                .thenReturn(List.of());

        Optional<Iterable<Measurement>> retrieved = service.getByCityName(city.getName());

        assertTrue(retrieved.isPresent());
        assertTrue(isEmptyIterable(retrieved.get()));
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCity")
    public void testGetByCityNameValid(City city, List<Measurement> objs) {
        when(cityService.getByName(city.getName()))
                .thenReturn(Optional.of(city));

        when(repository.findAllByIdCity(city))
                .thenReturn(objs);

        Optional<Iterable<Measurement>> retrieved = service.getByCityName(city.getName());

        assertTrue(retrieved.isPresent());
        assertObjsEqual(objs, retrieved.get());
    }

}
