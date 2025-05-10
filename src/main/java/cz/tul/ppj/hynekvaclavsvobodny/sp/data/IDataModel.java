package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import jakarta.persistence.*;

@MappedSuperclass
public interface IDataModel {
    @PrePersist
    void validate();
}