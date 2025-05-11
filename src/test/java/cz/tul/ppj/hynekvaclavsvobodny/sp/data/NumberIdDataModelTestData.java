package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import java.util.stream.Stream;


public abstract class NumberIdDataModelTestData<E extends NumberIdDataModel> extends DataModelTestData<E, Integer> {

    @Override
    public Stream<Integer> idsValid() {
        return Stream.of(null, 0, 1, 3, 12, 12345);
    }

    @Override
    public Stream<Integer> idsInvalid() {
        return Stream.of(-1, -2, -3, -120, -12345);
    }
}
