package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import java.util.Objects;

public abstract class NumberIdDataModel implements IDataModel {
    private Integer id = null;

    public NumberIdDataModel() {}

    public NumberIdDataModel(Integer id) {
        this.setId(id);
    }

    public Integer getId() {
        return id;
    }

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
