package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.DataModelRepository;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.DataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public class CrudDelegate<S extends DataModelService<R,E,ID>, R extends DataModelRepository<E, ID>, E extends IDataModel<ID>, ID extends Serializable> {

    @Autowired
    protected S service;

    public ResponseEntity<Iterable<E>> getAll() {
        return ResponseEntity.ofNullable(service.getAll());
    }

    public ResponseEntity<E> getById(ID id) {
        return ResponseEntity.of(service.getById(id));
    }

    public ResponseEntity<?> create(E obj) {
        return ResponseEntity.ok(service.create(obj));
    }

    public ResponseEntity<?> update(E obj, ID id) {
        service.update(obj, id);
        return ResponseEntity.ok(obj.getId());
    }

    public ResponseEntity<?> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok().build();
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
