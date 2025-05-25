package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static cz.tul.ppj.hynekvaclavsvobodny.sp.data.TestDataUtils.*;

public class MeasurementRepositoryTest extends DataModelRepositoryTest<MeasurementRepository, Measurement, Measurement.MeasurementId, MeasurementTestData, MeasurementRepositoryTestHelper> {

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveLoadByIdComponents(Measurement obj) {
        repository.save(obj);

        Optional<Measurement> retrieved = repository.findById(obj.getCity(), obj.getDatetime());

        assertObjEqual(obj, retrieved);
    }

    private Stream<Arguments> objsValidGroupedByCity() {
        return mapToArguments(data.objsValidGroupedByCity());
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCity")
    public void testSaveLoadByCity(City city, List<Measurement> objs) {
        repository.saveAll(objs);

        Iterable<Measurement> retrieved = repository.findAllByIdCity(city);

        assertObjsEqual(objs, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCity")
    public void testDeleteByCity(City city, List<Measurement> objs) {
        repository.saveAll(objs);

        repository.deleteAllByIdCity(city);

        assertTrue(isEmptyIterable(repository.findAllByIdCity(city)));
    }

    @ParameterizedTest
    @MethodSource("objsValidGroupedByCity")
    public void testFindLatestByCity(City city, List<Measurement> objs) {
        repository.saveAll(objs);

        Optional<Measurement> latest = objs.stream().max(Comparator.comparing(Measurement::getDatetime));

        Optional<Measurement> retrieved = repository.findFirstByIdCityOrderByIdDatetimeDesc(city);

        assertEquals(latest, retrieved);
    }

}
