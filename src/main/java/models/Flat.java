package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flats")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flat_id")
    private int id;

    @Column(name = "flat_number")
    private String flatNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "house")
    private House house;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "flat_owners",
            joinColumns = {@JoinColumn(name = "flat_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    List<Person> flatOwners;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "flat_registered",
            joinColumns = {@JoinColumn(name = "flat_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    List<Person> flatRegistered;

    public void addFlatOwners(Person person) {
        flatOwners.add(person);
    }

    public void removeFlatOwners(Person person) {
        flatOwners.remove(person);
    }

    public void addFlatRegistered(Person person) {
        flatRegistered.add(person);
    }

    public void removeFlatRegistered(Person person) {
        flatRegistered.remove(person);
    }

    public Flat() {
    }

    public Flat(String flatNumber, House house) {
        this.flatNumber = flatNumber;
        this.house = house;
        flatOwners = new ArrayList<>();
        flatRegistered = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public List<Person> getFlatOwners() {
        return flatOwners;
    }

    public void setFlatOwners(List<Person> flatOwners) {
        this.flatOwners = flatOwners;
    }

    public List<Person> getFlatRegistered() {
        return flatRegistered;
    }

    public void setFlatRegistered(List<Person> flatRegistered) {
        this.flatRegistered = flatRegistered;
    }

    @Override
    public String toString() {
        return "\n" + "кв. " + flatNumber + " " + house;
    }
}
