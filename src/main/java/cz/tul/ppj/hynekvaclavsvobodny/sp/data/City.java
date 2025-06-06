package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "City")
public class City extends NumberIdDataModel {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_code", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "id.city")
    @JsonIgnore
    private Set<Measurement> measurements = new HashSet<>();

    public City() {
        super();
    }

    public City(Integer id) {
        super(id);
    }

    public City(Integer id, String name, Country country) {
        this(id);
        this.setName(name);
        this.setCountry(country);
    }

    public City(String name, Country country) {
        this(null, name, country);
    }

    @PrePersist
    @Override
    public void validate() {
        Objects.requireNonNull(getName(), "'name' must not be null.");
        Objects.requireNonNull(getCountry(), "'country' must not be null.");
        getCountry().validate();
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @JsonIgnore
    public String getCountryId() {
        if (country == null) {
            return null;
        }

        return country.getId();
    }

    @JsonIgnore
    public String getCountryCode() {
        if (country == null) {
            return null;
        }

        return country.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof City city)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getName(), city.getName()) && Objects.equals(getCountry(), city.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getCountry());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", City.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("name='" + name + "'")
                .add("country=" + country)
                .toString();
    }
}