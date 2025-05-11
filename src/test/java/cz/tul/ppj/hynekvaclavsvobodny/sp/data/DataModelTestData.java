package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import java.io.Serializable;
import java.util.stream.Stream;

public abstract class DataModelTestData<E extends IDataModel<ID>, ID extends Serializable> {

    public abstract E emptyInstance();

    public Stream<E> objsValid() {
        return Stream.of();
    }

    public Stream<E> objsInvalid() {
        return Stream.of(emptyInstance());
    }

    public abstract Stream<ID> idsValid();

    public abstract Stream<ID> idsInvalid();

}
