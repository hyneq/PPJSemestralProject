package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public abstract class DataModelTestData<E extends IDataModel<ID>, ID extends Serializable> {

    private List<E> objsValidList = null;

    protected List<DataModelTestData<?,?>> dependencies = new LinkedList<>();

    protected void addDependency(DataModelTestData<?,?> dependency) {
        this.dependencies.add(dependency);
    }

    public abstract E emptyInstance();

    public Stream<E> objsValid() {
        return Stream.of();
    }

    public List<E> getObjsValid() {
        if (objsValidList == null) {
            objsValidList = objsValid().toList();
        }

        return objsValidList;
    }

    public Map<ID, E> getObjsValidById() {
        return TestDataUtils.mapByKey(getObjsValid(), E::getId);
    }

    public Stream<E> objsInvalid() {
        return Stream.of(emptyInstance());
    }

    public abstract Stream<ID> idsValid();

    public abstract Stream<ID> idsInvalid();

    public void resetDependencies() {
        for (DataModelTestData<?,?> dependency : dependencies) {
            dependency.reset();
        }
    }

    public void reset() {
        resetDependencies();
        objsValidList = null;
    }


}
