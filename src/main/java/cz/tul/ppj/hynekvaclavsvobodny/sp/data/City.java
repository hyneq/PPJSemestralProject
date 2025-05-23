package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "City")
public class City extends NumberIdDataModel {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

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

    public String getCountryId() {
        if (country == null) {
            return null;
        }

        return country.getId();
    }

    public String getCountryCode() {
        if (country == null) {
            return null;
        }

        return country.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof City city)) return false;
        return Objects.equals(getName(), city.getName()) && Objects.equals(getCountry(), city.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCountry());
    }
}