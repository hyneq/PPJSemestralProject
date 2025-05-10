package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import java.util.stream.Stream;

public abstract class DataModelTestData<T extends IDataModel> {

    public abstract T emptyInstance();

    public Stream<T> objsValid() {
        return Stream.of();
    }

    public Stream<T> objsInvalid() {
        return Stream.of(emptyInstance());
    }

}
