package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.MeasurementTestData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

public class MeasurementRepositoryTest extends DataModelRepositoryTest<MeasurementRepository, Measurement, Measurement.MeasurementId, MeasurementTestData, MeasurementRepositoryTestHelper> {

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveLoadByPrimaryKey(Measurement obj) {
        repository.save(obj);

        Measurement retrieved = repository.getById(obj.getCity(), obj.getDatetime());

        assertEquals(obj, retrieved);
    }

}
