package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int id;
    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Street> streets;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "city_of_residence",
            joinColumns = { @JoinColumn(name = "city_id") },
            inverseJoinColumns = { @JoinColumn(name = "person_id") }
    )
    List<Person> cityDwellers;

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
        streets = new ArrayList<>();
        cityDwellers = new ArrayList<>();
    }

    public void addStreet(Street street) {
        street.setCity(this);
        streets.add(street);
    }

    public void removeStreet(Street street) {
        streets.remove(street);
    }

    public void addCityDwellers(Person person) {
        cityDwellers.add(person);
    }

    public void removeCityDwellers(Person person) {
        cityDwellers.remove(person);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public List<Person> getCityDwellers() {
        return cityDwellers;
    }

    public void setCityDwellers(List<Person> cityDwellers) {
        this.cityDwellers = cityDwellers;
    }

    @Override
    public String toString() {
        return "\n" + "г. " + cityName;
    }
}
