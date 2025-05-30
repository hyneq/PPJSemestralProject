package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.DataModelTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions.IdMismatchException;
import cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions.ObjectAlreadyExistsException;
import cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions.ObjectDoesNotExistException;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.DataModelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static cz.tul.ppj.hynekvaclavsvobodny.sp.data.TestDataUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DataModelServiceTest<
        S extends DataModelService<R,E,ID>,
        R extends DataModelRepository<E,ID>,
        E extends IDataModel<ID>,
        ID extends Serializable,
        T extends DataModelTestData<E,ID>
    > {

    @MockitoBean
    protected R repository;

    @Autowired
    protected S service;

    @Autowired
    protected T data;

    public Stream<E> objsValid() {
        return data.objsValid();
    }

    @Test
    public void testSaveAllEmpty() {
        when(repository.findAll()).thenReturn(List.of());

        Iterable<E> retrieved = service.getAll();

        assertTrue(isEmptyIterable(retrieved));
    }

    @Test
    public void testSaveAllObjsValid() {
        List<E> objs = objsValid().toList();

        when(repository.findAll()).thenReturn(objs);

        Iterable<E> retrieved = service.getAll();

        assertObjsEqual(objs, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByIdEmpty(E obj) {
        when(repository.findById(obj.getId()))
                .thenReturn(Optional.empty());

        Optional<E> retrieved = service.getById(obj.getId());

        assertTrue(retrieved.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testGetByIdObjsValid(E obj) {
        when(repository.findById(obj.getId()))
                .thenReturn(Optional.of(obj));

        Optional<E> retrieved = service.getById(obj.getId());

        assertObjEqual(obj, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testExistsEmpty(E obj) {
        when(repository.existsById(obj.getId()))
                .thenReturn(false);

        assertFalse(service.exists(obj));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testExistsObjsValid(E obj) {
        when(repository.existsById(obj.getId()))
                .thenReturn(true);

        assertTrue(service.exists(obj));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testCreateObjsValid(E obj) {
        when(repository.existsById(obj.getId()))
                .thenReturn(false);

        when(repository.save(obj))
                .thenReturn(obj);

        ID retrievedId = service.create(obj);

        assertEquals(obj.getId(), retrievedId);
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testCreateExists(E obj) {
        when(repository.existsById(obj.getId()))
                .thenReturn(true);

        assertThrows(ObjectAlreadyExistsException.class,
                () -> service.create(obj));
    }

    @Test
    public void testUpdateIdMismatch() {
        Iterator<ID> ids = data.idsValid().filter(Objects::nonNull).iterator();

        E obj = data.emptyInstance();
        obj.setId(ids.next());

        ID mismatchedId = ids.next();

        assertThrows(IdMismatchException.class,
                () -> service.update(obj, mismatchedId));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testUpdateDoesNotExist(E obj) {
        when(repository.existsById(obj.getId()))
                .thenReturn(false);

        assertThrows(ObjectDoesNotExistException.class,
                () -> service.update(obj));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testUpdateObjsValid(E obj) {
        when(repository.existsById(obj.getId()))
                .thenReturn(true);

        when(repository.save(obj))
                .thenReturn(obj);
    }

    @Test
    public void testDeleteAll() {
        doNothing().when(repository).deleteAll();

        service.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDelete(E obj) {
        doNothing().when(repository).delete(obj);

        service.delete(obj);
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteById(E obj) {
        doNothing().when(repository).deleteById(obj.getId());

        service.delete(obj.getId());
    }
}
