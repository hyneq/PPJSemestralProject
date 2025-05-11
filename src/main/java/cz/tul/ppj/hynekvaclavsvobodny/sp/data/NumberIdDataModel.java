package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import jakarta.persistence.*;

import java.util.Objects;

@MappedSuperclass
public abstract class NumberIdDataModel implements IDataModel<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public NumberIdDataModel() {}

    public NumberIdDataModel(Integer id) {
        this.setId(id);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        if (id != null && id < 0) {
            throw new IllegalArgumentException("The argument 'id' must be positive or null.");
        }

        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NumberIdDataModel that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
