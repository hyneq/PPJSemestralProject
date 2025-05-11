package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import java.util.stream.Stream;

public abstract class DataModelTestData<E extends IDataModel<?>> {

    public abstract E emptyInstance();

    public Stream<E> objsValid() {
        return Stream.of();
    }

    public Stream<E> objsInvalid() {
        return Stream.of(emptyInstance());
    }

}
