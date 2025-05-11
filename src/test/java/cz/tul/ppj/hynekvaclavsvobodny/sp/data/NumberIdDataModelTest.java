package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public abstract class NumberIdDataModelTest<E extends NumberIdDataModel, T extends NumberIdDataModelTestData<E>> extends DataModelTest<E, T, Integer> {

    @Override
    public void assertEmpty(E obj) {
        super.assertEmpty(obj);
        assertNull(obj.getId());
    }

}
