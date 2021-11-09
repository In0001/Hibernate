package models;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "pasport_number")
    private int pasportNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "flat_owners",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "flat_id") }
    )
    List<Flat> ownedFlats;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "flat_registered",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "flat_id") }
    )
    List<Flat> flatsWithRegistration;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "city_of_residence",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "city_id") }
    )
    List<City> registrationCities;

    public Person() {
    }

    public Person(String lastName, String firstName, String secondName, Date dateOfBirth, int pasportNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.pasportNumber = pasportNumber;
    }

    public int getPerson_id() {
        return id;
    }

    public void setPerson_id(int person_id) {
        this.id = person_id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getPasportNumber() {
        return pasportNumber;
    }

    public void setPasportNumber(int pasportNumber) {
        this.pasportNumber = pasportNumber;
    }

    public List<Flat> getOwnedFlats() {
        return ownedFlats;
    }

    public void setOwnedFlats(List<Flat> ownedFlats) {
        this.ownedFlats = ownedFlats;
    }

    public List<Flat> getFlatsWithRegistration() {
        return flatsWithRegistration;
    }

    public void setFlatsWithRegistration(List<Flat> flatsWithRegistration) {
        this.flatsWithRegistration = flatsWithRegistration;
    }

    public List<City> getRegistrationCities() {
        return registrationCities;
    }

    public void setRegistrationCities(List<City> registrationCities) {
        this.registrationCities = registrationCities;
    }

    @Override
    public String toString() {
        return "\n" + id + " " + lastName + " " + firstName + " " + secondName + ", " + dateOfBirth + ", " + pasportNumber;
    }
}
