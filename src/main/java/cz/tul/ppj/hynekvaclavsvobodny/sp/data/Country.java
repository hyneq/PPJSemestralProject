package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Country")
public class Country extends NumberIdDataModel {

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<City> cities;

    public Country() {
        super();
    }

    public Country(Integer id) {
        super(id);
    }

    public Country(Integer id, String code, String name) {
        this(id);
        this.setCode(code);
        this.setName(name);
    }

    public Country(String code, String name) {
        this(null, code, name);
        this.setCode(code);
        this.setName(name);
    }

    @PrePersist
    @Override
    public void validate() {
        Objects.requireNonNull(getCode(), "'code' must not be null.");
        Objects.requireNonNull(getName(), "'name' must not be null.");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code != null && !code.matches("^[A-Z][A-Z]$")) {
            throw new IllegalArgumentException("The argument 'code' must match '^[A-Z][A-Z]$' or be null");
        }

        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.isEmpty()) {
            throw new IllegalArgumentException("The argument 'name' must not be empty");
        }

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Country country)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCode(), country.getCode()) && Objects.equals(getName(), country.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCode(), getName());
    }
}