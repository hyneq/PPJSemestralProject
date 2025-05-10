package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CityTest extends NumberIdDataModelTest<City, CityTestData> {

    @Override
    public void assertEmpty(City city) {
        super.assertEmpty(city);

        assertNull(city.getName());
        assertNull(city.getCountry());
        assertNull(city.getCountryId());
    }

    private static final List<String> namesValid = Arrays.asList(null, "Liberec", "Berlin", "London");

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

    @Test
    public void testSetCountry() {
        obj.setCountry(new Country());
    }

    @Test
    public void testSetCountryNull() {
        obj.setCountry(null);
    }

}
