package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDataUtils {
    private TestDataUtils() {}

    public static <T,K> Map<K,T> mapByKey(Stream<T> objs, Function<? super T, ? extends K> keyMapper) {
        return objs.collect(Collectors.toMap(
                keyMapper,
                Function.identity(),
                (existing, replacement) -> existing
        ));
    }

    public static <T,K> Map<K,T> mapByKey(List<T> objs, Function<? super T, ? extends K> keyMapper) {
        return mapByKey(objs.stream(), keyMapper);
    }
}
