package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModelTestData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public abstract class NumberIdDataModelRepositoryTest<
            R extends NumberIdDataModelRepository<E>, E extends NumberIdDataModel, T extends NumberIdDataModelTestData<E>, S extends NumberIdDataModelRepositoryTestHelper<R,E,T>
        >  extends DataModelRepositoryTest<R, E, Integer, T, S> {

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveLoadById(E obj) {
        assertNull(obj.getId());
        repository.save(obj);
        assertNotNull(obj.getId());

        Optional<E> retrieved = repository.findById(obj.getId());

        assertTrue(retrieved.isPresent());

        assertEquals(obj, retrieved.get());
    }
}
