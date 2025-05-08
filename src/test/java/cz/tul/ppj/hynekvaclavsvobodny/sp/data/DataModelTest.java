package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DataModelTest<T extends IDataModel, S extends DataModelTestData<T>> {

    @Autowired
    protected S data;

    protected T obj;

    @BeforeEach
    public void initialize() {
        obj = data.emptyInstance();
    }

    public void assertEmpty(T obj) {
        assertNotNull(obj);
    }

    @Test
    public void testCreate() {
        T obj = data.emptyInstance();

        assertEmpty(obj);
    }

    protected Stream<T> objsValid() {
        return data.objsValid();
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testValdiateValid(T obj) {
        obj.validate();
    }

    protected Stream<T> objsInvalid() {
        return data.objsInvalid();
    }

    @ParameterizedTest
    @MethodSource("objsInvalid")
    public void testValidateInvalid(T obj) {
        assertThrows(Exception.class,
                obj::validate);
    }

}
