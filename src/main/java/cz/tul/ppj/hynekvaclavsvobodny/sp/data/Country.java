package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Country")
public class Country implements IDataModel<String> {

    @Id
    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<City> cities = new HashSet<>();

    public Country() {
        super();
    }

    public Country(String code, String name) {
        this.setCode(code);
        this.setName(name);
    }

    @PrePersist
    @Override
    public void validate() {
        Objects.requireNonNull(getCode(), "'code' must not be null.");
        Objects.requireNonNull(getName(), "'name' must not be null.");
    }

    @Override
    public String getId() {
        return getCode();
    }

    @Override
    public void setId(String id) {
        setCode(id);
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
        return Objects.equals(getCode(), country.getCode()) && Objects.equals(getName(), country.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getName());
    }
}