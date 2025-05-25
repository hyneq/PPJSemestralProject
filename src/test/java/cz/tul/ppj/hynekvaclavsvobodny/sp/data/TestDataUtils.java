package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDataUtils {
    private TestDataUtils() {}

    public static <K,V> Map<K,List<V>> mapWithFixedKeys(List<K> keys, Map<K,List<V>> map) {
        return keys.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        key -> map.getOrDefault(key, List.of())
                ));
    }

    public static <K,V> Map<K, V> mapByKey(
            Stream<V> objs,
            Function<? super V, ? extends K> keyMapper
    ) {
        return objs.collect(Collectors.toMap(
                keyMapper,
                Function.identity(),
                (existing, replacement) -> existing
        ));
    }

    public static <K,V> Map<K, V> mapByKey(
            List<V> objs,
            Function<? super V, ? extends K> keyMapper
    ) {
        return mapByKey(objs.stream(), keyMapper);
    }

    public static <K,V> Map<K,List<V>> groupBy(
            Stream<V> objs,
            Function<? super V, ? extends K> classifier
    ) {
        return objs
                .collect(Collectors.groupingBy(classifier));
    }

    public static <K,V> Map<K,List<V>> groupBy(
            List<V> objs,
            Function<? super V, ? extends K> classifier
    ) {
        return groupBy(objs.stream(), classifier);
    }

    public static <K,V> Map<K,List<V>> groupByFixed(
            List<K> keys,
            Stream<V> objs,
            Function<? super V, ? extends K> classifier
    ) {
        return mapWithFixedKeys(keys, groupBy(objs, classifier));
    }

    public static <V,K> Map<K,List<V>> groupByFixed(
            List<K> keys,
            List<V> objs,
            Function<? super V, ? extends K> classifier
    ) {
        return mapWithFixedKeys(keys, groupBy(objs, classifier));
    }

    public static <V,K> Stream<Arguments> mapToArguments(Map<K, V> map) {
        return map.entrySet().stream().map(entry -> Arguments.of(entry.getKey(), entry.getValue()));
    }

    public static <T extends IDataModel<?>> void assertObjEqual(T expected, Optional<T> actual) {
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    public static <T extends IDataModel<?>> void assertObjsEqual(List<T> expected, Iterable<T> actual) {
        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    public static <T extends IDataModel<?>> boolean isEmptyIterable(Iterable<T> iterable) {
        return !iterable.iterator().hasNext();
    }

}
