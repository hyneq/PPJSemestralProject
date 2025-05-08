package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CountryTest extends NumberIdDataModelTest<Country, CountryTestData> {

    @Override
    public void assertEmpty(Country country) {
        super.assertEmpty(country);

        assertNull(country.getCode());
        assertNull(country.getName());
    }

    private static final List<String> codesValid = Arrays.asList(null, "CZ", "UK", "US", "FR", "DE");

    @ParameterizedTest
    @FieldSource("codesValid")
    public void testCodeValid(String code) {
        obj.setCode(code);

        assertEquals(code, obj.getCode());
    }

    private static final List<String> codesInvalid = Arrays.asList("", "cz", "fr", "de", "Czechia", "Cz", "cZ", "ukraIne");

    @ParameterizedTest
    @FieldSource("codesInvalid")
    public void testCodeInvalid(String code) {
        assertThrows(IllegalArgumentException.class,
                () -> obj.setCode(code));
    }

    private static final List<String> namesValid = Arrays.asList(null, "Czechia", "Germany", "United Kingdom");

    @ParameterizedTest
    @FieldSource("namesValid")
    public void testNameValid(String name) {
        obj.setName(name);

        assertEquals(name, obj.getName());
    }

    private static final List<String> namesInvalid = List.of("");

    @ParameterizedTest
    @FieldSource("namesInvalid")
    public void testNameInvalid(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> obj.setName(name));
    }

}