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
public abstract class DataModelRepositoryTest<R extends DataModelRepository<E, ID>, E extends IDataModel<ID>, ID extends Serializable, T extends DataModelTestData<E, ID>> {

    protected E obj;

    @Autowired
    protected T data;

    @Autowired
    protected R repository;

    @BeforeEach
    public void initialize() {
        obj = data.emptyInstance();
    }

    @Test
    public void testRepositoryLoads() {
        assertNotNull(repository);
    }

    private Stream<E> objsValid() {
        return data.objsValid();
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveValid(E obj) {
        repository.save(obj);
    }

    private Stream<E> objsInvalid() {
        return data.objsInvalid();
    }

    @ParameterizedTest
    @MethodSource("objsInvalid")
    public void testSaveInvalid(E obj) {
        assertThrows(Exception.class,
                () -> repository.save(obj));
}

}