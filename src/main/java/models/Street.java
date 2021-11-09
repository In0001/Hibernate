package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "streets")
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "street_id")
    private int id;
    @Column(name = "street_name")
    private String streetName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city")
    private City city;

    @OneToMany(mappedBy = "street", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<House> houses;

    public Street() {
    }

    public Street(String streetName, City city) {
        this.streetName = streetName;
        this.city = city;
        houses = new ArrayList<>();
    }

    public void addHouse(House house) {
        house.setStreet(this);
        houses.add(house);
    }

    public void removeHouse(House house) {
        houses.remove(house);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    @Override
    public String toString() {
        return "\n" + "ул. " + streetName + " " + city;
    }
}
