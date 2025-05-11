package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.DataModelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class DataModelService<T extends DataModelRepository<E, ID>, E extends IDataModel<ID>, ID extends Serializable> {

    protected final String modelName;

    @Autowired
    protected T repository;

    protected DataModelService(String modelName) {
        this.modelName = modelName;
    }

    public Iterable<E> getAll() {
        return repository.findAll();
    }

    public boolean exists(E obj) {
        return repository.existsById(obj.getId());
    }

    public void create(E obj) {
        if (exists(obj)) {
            throw new IllegalArgumentException(String.format("Object (%s) already exists in the database", modelName));
        }

        try {
            repository.save(obj);
        } catch (Exception e) {
            // TODO log
            throw new IllegalArgumentException("Object (%s) save failed");
        }
    }

    public void update(E obj) {
        if (!exists(obj)) {
            throw new IllegalArgumentException(String.format("Object (%s) does not exist in the database, cannot update.", modelName));
        }

        try {
            repository.save(obj);
        } catch (Exception e) {
            // TODO log
            throw new IllegalArgumentException("Object (%s) save failed");
        }
    }

    public void delete(E obj) {
        repository.delete(obj);
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }

}
