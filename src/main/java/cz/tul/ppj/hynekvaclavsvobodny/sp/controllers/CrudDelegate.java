package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions.ObjectAlreadyExistsException;
import cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions.ObjectDoesNotExistException;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.DataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CrudDelegate<S extends DataModelService<?,E,ID>, E extends IDataModel<ID>, ID extends Serializable> {

    @Autowired
    protected S service;

    public ResponseEntity<Iterable<E>> getAll() {
        return ResponseEntity.ofNullable(service.getAll());
    }

    public ResponseEntity<E> getById(ID id) {
        return ResponseEntity.of(service.getById(id));
    }

    public ResponseEntity<?> create(E obj) {
        try {
            return ResponseEntity.ok(service.create(obj));
        } catch (ObjectAlreadyExistsException e) {
            return new ResponseEntity<>("Object already exists", HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> update(E obj, ID id) {
        try {
            service.update(obj, id);
            return ResponseEntity.ok(obj.getId());
        } catch (ObjectDoesNotExistException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> delete(E obj) {
        service.delete(obj);
        return ResponseEntity.ok(obj.getId());
    }

    public ResponseEntity<?> delete(ID id) {
        service.delete(id);
        return ResponseEntity.ok(id);
    }


}
