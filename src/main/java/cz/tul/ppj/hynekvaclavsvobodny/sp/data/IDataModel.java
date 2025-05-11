package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public interface IDataModel<ID extends Serializable> {
    @PrePersist
    void validate();

    ID getId();

    void setId(ID id);
}