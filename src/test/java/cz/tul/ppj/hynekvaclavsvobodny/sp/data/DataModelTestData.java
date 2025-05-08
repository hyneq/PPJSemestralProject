package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import java.util.stream.Stream;

public abstract class DataModelTestData<T extends IDataModel> {

    public abstract T emptyInstance();

    protected Stream<T> objsValid() {
        return Stream.of();
    }

    protected Stream<T> objsInvalid() {
        return Stream.of(emptyInstance());
    }

}
