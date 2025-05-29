package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@MappedSuperclass
public interface IDataModel<ID extends Serializable> {
    @PrePersist
    void validate();

    ID getId();

    void setId(ID id);
}