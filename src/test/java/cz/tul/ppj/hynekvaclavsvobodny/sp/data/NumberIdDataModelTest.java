package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public abstract class NumberIdDataModelTest<T extends NumberIdDataModel, S extends NumberIdDataModelTestData<T>> extends DataModelTest<T, S> {

    @Override
    public void assertEmpty(T obj) {
        super.assertEmpty(obj);
        assertNull(obj.getId());
    }

    protected List<Integer> idsValid() {
        return data.idsValid;
    }

    @ParameterizedTest
    @MethodSource("idsValid")
    public void testIdValid(Integer id) {
        obj.setId(id);

        assertEquals(id, obj.getId());
    }

    protected List<Integer> idsInvalid() {
        return data.idsInvalid;
    }

    @ParameterizedTest
    @MethodSource("idsInvalid")
    public void testIdInvalid(Integer id) {
        assertThrows(IllegalArgumentException.class,
                () -> obj.setId(id));
    }

}
