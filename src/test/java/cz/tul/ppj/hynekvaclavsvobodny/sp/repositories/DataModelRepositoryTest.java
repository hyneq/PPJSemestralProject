package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.DataModelTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static cz.tul.ppj.hynekvaclavsvobodny.sp.data.TestDataUtils.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public abstract class DataModelRepositoryTest<
        R extends DataModelRepository<E, ID>, E extends IDataModel<ID>, ID extends Serializable, T extends DataModelTestData<E, ID>, S extends DataModelRepositoryTestHelper<R,E,T>
    > {

    protected E obj;

    @Autowired
    protected T data;

    @Autowired
    protected R repository;

    @Autowired
    protected S helper;

    @BeforeAll
    public void initializeAll() {
        data.reset();
        helper.persistDependencies();
    }

    @BeforeEach
    public void initializeEach() {
        obj = data.emptyInstance();
    }

    @AfterAll
    public void deinitializeAll() {
        helper.resetDependencies();
    }

    @Test
    public void testRepositoryLoads() {
        assertNotNull(repository);
    }

    @Test
    public void testRepoEmpty() {
        assertTrue(isEmptyIterable(repository.findAll()));
    }

    protected Stream<E> objsValid() {
        return data.objsValid();
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveValid(E obj) {
        repository.save(obj);

        assertTrue(repository.existsById(obj.getId()));
    }

    protected Stream<E> objsInvalid() {
        return data.objsInvalid();
    }

    @ParameterizedTest
    @MethodSource("objsInvalid")
    public void testSaveInvalid(E obj) {
        assertThrows(Exception.class,
                () -> repository.save(obj));
    }

    @Test
    public void testSaveAllEmpty() {
        repository.saveAll(List.of());

        assertTrue(isEmptyIterable(repository.findAll()));
    }

    @Test
    public void testSaveAllValid() {
        List<E> objs = objsValid().toList();

        repository.saveAll(objs);

        assertTrue(objs.stream().allMatch((obj) -> repository.existsById(obj.getId())));
    }

    @Test
    public void testSaveAllInvalid() {
        List<E> objs = objsInvalid().toList();

        assertThrows(Exception.class,
                () -> repository.saveAll(objs));
    }

    @Test
    public void testSaveLoadAll() {
        List<E> objs = objsValid().toList();

        repository.saveAll(objs);

        Iterable<E> retrieved = repository.findAll();

        assertObjsEqual(objs, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveLoadById(E obj) {
        repository.save(obj);

        Optional<E> retrieved = repository.findById(obj.getId());

        assertObjEqual(obj, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDelete(E obj) {
        repository.save(obj);

        repository.delete(obj);

        assertFalse(repository.existsById(obj.getId()));
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteById(E obj) {
        repository.save(obj);

        repository.deleteById(obj.getId());

        assertFalse(repository.existsById(obj.getId()));
    }

    @Test
    public void testDeleteAll() {
        repository.saveAll(objsValid().toList());

        repository.deleteAll();

        assertTrue(isEmptyIterable(repository.findAll()));
    }

}