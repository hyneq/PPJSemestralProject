package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.CountryTestData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static cz.tul.ppj.hynekvaclavsvobodny.sp.data.TestDataUtils.*;

@SpringBootTest
public class CountryRepositoryTest extends DataModelRepositoryTest<CountryRepository, Country, String, CountryTestData, CountryRepositoryTestHelper> {

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveLoadByCode(Country obj) {
        repository.save(obj);

        Optional<Country> retrieved = repository.findByCode(obj.getCode());

        assertObjEqual(obj, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testSaveLoadByName(Country obj) {
        repository.save(obj);

        Optional<Country> retrieved = repository.findByName(obj.getName());

        assertObjEqual(obj, retrieved);
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteByCode(Country obj) {
        repository.save(obj);

        repository.deleteByCode(obj.getCode());

        assertTrue(repository.findByCode(obj.getCode()).isEmpty());
    }

    @ParameterizedTest
    @MethodSource("objsValid")
    public void testDeleteByName(Country obj) {
        repository.save(obj);

        repository.deleteByName(obj.getName());

        assertTrue(repository.findByName(obj.getName()).isEmpty());
    }

}
