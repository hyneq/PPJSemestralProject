package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions.IdMismatchException;
import cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions.ObjectAlreadyExistsException;
import cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions.ObjectDoesNotExistException;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.DataModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Optional;

public abstract class DataModelService<R extends DataModelRepository<E, ID>, E extends IDataModel<ID>, ID extends Serializable> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

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
            logger.error("Object create failed: {}", obj, e);
            throw new IllegalArgumentException(e);
        }
    }

    public void update(E obj, ID id) {
        if (obj.getId() == null) {
            obj.setId(id);
        } else if (id != null && !obj.getId().equals(id)) {
            throw new IdMismatchException(obj);
        }

        if (!exists(obj)) {
            throw new ObjectDoesNotExistException(obj);
        }

        try {
            repository.save(obj);
        } catch (Exception e) {
            logger.error("Object save failed: {}", obj, e);
            throw new RuntimeException(e);
        }
    }

    public void update(E obj) {
        update(obj, null);
    }

    public void deleteAll() {
        try {
            repository.deleteAll();
        } catch (Exception e) {
            logger.error("Deleting all failed");
        }
    }

    public void delete(E obj) {
        try {
            repository.delete(obj);
        } catch (Exception e) {
            logger.error("Object delete failed: {}", obj, e);
            throw new RuntimeException(e);
        }
    }

    public void delete(ID id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            logger.error("Object delete by id failed: id={}", id, e);
            throw new RuntimeException(e);
        }
    }

}
