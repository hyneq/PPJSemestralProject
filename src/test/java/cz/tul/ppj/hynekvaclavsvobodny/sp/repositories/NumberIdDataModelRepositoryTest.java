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
        T extends NumberIdDataModelRepository<S>, S extends NumberIdDataModel, V extends NumberIdDataModelTestData<S>
        >  extends DataModelRepositoryTest<T, S, Integer, V> {

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveLoadById(S obj) {
        assertNull(obj.getId());
        repository.save(obj);
        assertNotNull(obj.getId());

        Optional<S> retrieved = repository.findById(obj.getId());

        assertTrue(retrieved.isPresent());

        assertEquals(obj, retrieved.get());
    }
}
