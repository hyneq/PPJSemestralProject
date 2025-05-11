package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DataModelTest<E extends IDataModel<ID>, T extends DataModelTestData<E, ID>, ID extends Serializable> {

    @Autowired
    protected T data;

    protected E obj;

    @BeforeEach
    public void initialize() {
        obj = data.emptyInstance();
    }

    public void assertEmpty(E obj) {
        assertNotNull(obj);
    }

    @Test
    public void testCreate() {
        E obj = data.emptyInstance();

        assertEmpty(obj);
    }

    protected Stream<E> objsValid() {
        return data.objsValid();
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testValidateValid(E obj) {
        obj.validate();
    }

    protected Stream<E> objsInvalid() {
        return data.objsInvalid();
    }

    @ParameterizedTest
    @MethodSource("objsInvalid")
    public void testValidateInvalid(E obj) {
        assertThrows(Exception.class,
                obj::validate);
    }

    protected Stream<ID> idsValid() {
        return data.idsValid();
    }

    @ParameterizedTest
    @MethodSource("idsValid")
    public void testIdValid(ID id) {
        obj.setId(id);

        assertEquals(id, obj.getId());
    }

    protected Stream<ID> idsInvalid() {
        return data.idsInvalid();
    }

    @ParameterizedTest
    @MethodSource("idsInvalid")
    public void testIdInvalid(ID id) {
        assertThrows(IllegalArgumentException.class,
                () -> obj.setId(id));
    }

}
