package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.DataModelTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public abstract class DataModelRepositoryTest<T extends DataModelRepository<S, ID>, S extends IDataModel, ID extends Serializable, V extends DataModelTestData<S>> {

    protected S obj;

    @Autowired
    protected V data;

    @Autowired
    protected T repository;

    @BeforeEach
    public void initialize() {
        obj = data.emptyInstance();
    }

    @Test
    public void testRepositoryLoads() {
        assertNotNull(repository);
    }

    private Stream<S> objsValid() {
        return data.objsValid();
    }

    @ParameterizedTest
    @MethodSource("objsValid")
        public void testSaveValid(S obj) {
            repository.save(obj);
        }

        private Stream<S> objsInvalid() {
            return data.objsInvalid();
        }

        @ParameterizedTest
        @MethodSource("objsInvalid")
        public void testSaveInvalid(S obj) {
            assertThrows(Exception.class,
                    () -> repository.save(obj));
    }

}