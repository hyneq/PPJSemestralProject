package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions.IdMismatchException;
import cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions.ObjectAlreadyExistsException;
import cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions.ObjectDoesNotExistException;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.DataModelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Optional;

public abstract class DataModelService<R extends DataModelRepository<E, ID>, E extends IDataModel<ID>, ID extends Serializable> {

    protected final String modelName;

    @Autowired
    protected R repository;

    protected DataModelService(String modelName) {
        this.modelName = modelName;
    }

    public Iterable<E> getAll() {
        return repository.findAll();
    }

    public Optional<E> getById(ID id) {
        return repository.findById(id);
    }

    public boolean exists(E obj) {
        return repository.existsById(obj.getId());
    }

    public ID create(E obj) {
        if (exists(obj)) {
            throw new ObjectAlreadyExistsException(obj);
        }

        try {
            repository.save(obj);
            return obj.getId();
        } catch (Exception e) {
            // TODO log
            throw new IllegalArgumentException("Object (%s) save failed");
        }
    }

    public void update(E obj, ID id) {
        if (obj.getId() == null) {
            obj.setId(id);
        } else if (!obj.getId().equals(id)) {
            throw new IdMismatchException(obj);
        }

        if (!exists(obj)) {
            throw new ObjectDoesNotExistException(obj);
        }

        try {
            repository.save(obj);
        } catch (Exception e) {
            // TODO log
            throw new IllegalArgumentException("Object (%s) save failed");
        }
    }

    public void update(E obj) {
        update(obj, null);
    }

    public void delete(E obj) {
        repository.delete(obj);
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }

}
