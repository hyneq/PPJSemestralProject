package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public abstract class NumberIdDaoTest<
        T extends NumberIdDataModelDao<S>, S extends NumberIdDataModel, V extends NumberIdDataModelTestData<S>
    >  extends DaoTest<T, S, V> {

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveLoadById(S obj) {
        assertNull(obj.getId());
        dao.create(obj);
        assertNotNull(obj.getId());

        S retrieved = dao.get(obj.getId());

        assertEquals(obj, retrieved);
    }
}
