package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private int id;
    @Column(name = "house_number")
    private String houseNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "street")
    private Street street;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Flat> flats;

    public House() {
    }

    public House(String houseNumber, Street street, List<Flat> flats) {
        this.houseNumber = houseNumber;
        this.street = street;
        flats = new ArrayList<>();
    }

    public void addFlat(Flat flat) {
        flat.setHouse(this);
        flats.add(flat);
    }

    public void removeFlat(Flat flat) {
        flats.remove(flat);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }

    @Override
    public String toString() {
        return "\n" + "дом " + houseNumber + " " + street;
    }
}
