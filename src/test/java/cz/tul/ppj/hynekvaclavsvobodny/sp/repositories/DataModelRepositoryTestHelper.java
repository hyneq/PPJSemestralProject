package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.DataModelTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class DataModelRepositoryTestHelper<R extends DataModelRepository<E, ?>, E extends IDataModel<?>, T extends DataModelTestData<E, ?>> {

    @Autowired
    protected R repository;

    @Autowired
    protected T testData;

    protected List<DataModelRepositoryTestHelper<?,?,?>> dependencies = new LinkedList<>();

    protected void addDependency(DataModelRepositoryTestHelper<?,?,?> dependency) {
        this.dependencies.add(dependency);
    }

    public void persistDependencies() {
        for (DataModelRepositoryTestHelper<?, ?, ?> dependency : dependencies) {
            dependency.persist();
        }
    }

    public void persistSelf() {
        repository.saveAll(testData.getObjsValid());
    }

    public void persist() {
        persistDependencies();
        persistSelf();
    }

    public void resetDependencies() {
        for (DataModelRepositoryTestHelper<?, ?, ?> dependency : dependencies) {
            dependency.reset();
        }
    }

    public void resetSelf() {
        repository.deleteAll();
    }

    public void reset() {
        resetSelf();
        resetDependencies();
    }

}
