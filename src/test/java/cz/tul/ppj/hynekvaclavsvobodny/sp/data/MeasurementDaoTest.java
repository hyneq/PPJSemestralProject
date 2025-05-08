package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

public class MeasurementDaoTest extends DaoTest<MeasurementDao, Measurement, MeasurementTestData> {

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveLoadByPrimaryKey(Measurement obj) {
        dao.create(obj);

        Measurement retrieved = dao.get(obj.getCity_id(), obj.getDatetime());

        assertEquals(obj, retrieved);
    }
}
