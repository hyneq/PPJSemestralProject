package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public abstract class DaoTest<T extends Dao<S>, S extends IDataModel, V extends DataModelTestData<S>> {

    protected S obj;

    @Autowired
    protected V data;

    @Autowired
    protected T dao;

    @BeforeEach
    public void initialize() {
        obj = data.emptyInstance();
    }

    @Test
    public void testDaoLoads() {
        assertNotNull(dao);
    }

    private Stream<S> objsValid() {
        return data.objsValid();
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testCreateValid(S obj) {
        dao.create(obj);
    }

    private Stream<S> objsInvalid() {
        return data.objsInvalid();
    }

    @ParameterizedTest
    @MethodSource("objsInvalid")
    public void testCreateInvalid(S obj) {
        assertThrows(Exception.class,
                () -> dao.create(obj));
    }

}
