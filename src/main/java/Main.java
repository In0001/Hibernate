import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import models.*;
import services.*;

public class Main {

    public static void printDelimiter() {
        System.out.println("--------------------------------------------------------------");
    }

    public static void findPeopleRegisteredInGivenFlat(int id) throws SQLException {
        System.out.println("Люди, прописанные в заданной квартире:");
        FlatService flatService = new FlatService();
        Flat flat = flatService.findFlat(id);
        List<Person> personList = flat.getFlatRegistered();
        System.out.println(personList);
    }

    public static void findPeopleOwningGivenFlat(int id) throws SQLException {
        System.out.println("Люди, владеющие заданной квартирой:");
        FlatService flatService = new FlatService();
        Flat flat = flatService.findFlat(id);
        List<Person> personList = flat.getFlatOwners();
        System.out.println(personList.stream().distinct().collect(Collectors.toList()));
    }

    public static void findPeopleRegisteredInGivenCity(int id) throws SQLException {
        System.out.println("Люди, прописанные в заданном городе:");
        CityService cityService = new CityService();
        City city = cityService.findCity(id);
        List<Street> streetList = city.getStreets();
        List<House> houseList = new ArrayList<>();
        List<Flat> flatList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();

        for (Street street: streetList)
            houseList.addAll(street.getHouses());

        for (House house: houseList)
            flatList.addAll(house.getFlats());

        for (Flat flat: flatList)
            personList.addAll(flat.getFlatRegistered());

        System.out.println(personList.stream().distinct().collect(Collectors.toList()));
    }

    public static void findPeopleRegisteredInGivenHouse(int id) throws SQLException {
        System.out.println("Люди, прописанные в заданном доме:");
        HouseService houseService = new HouseService();
        House house = houseService.findHouse(id);
        List<Flat> flatList = house.getFlats();
        List<Person> personList = new ArrayList<>();

        for (Flat flat: flatList)
            personList.addAll(flat.getFlatRegistered());

        System.out.println(personList);
    }

    public static void findPeopleRegisteredOnGivenListOfStreets(List<Integer> streetList) throws SQLException {
        System.out.println("Люди, прописанные на улицах из заданного списка:");
        StreetService streetService = new StreetService();

        List<House> houseList = new ArrayList<>();
        List<Flat> flatList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();

        for (int id: streetList) {
            Street street = streetService.findStreet(id);
            houseList.addAll(street.getHouses());
        }

        for (House house: houseList)
            flatList.addAll(house.getFlats());

        for (Flat flat: flatList)
            personList.addAll(flat.getFlatRegistered());

        System.out.println(personList);
    }

    public static void deletePersonFromFlat(int person_id, int flat_id) throws SQLException {
        System.out.println("Выписывание из квартиры");
        PersonService personService = new PersonService();
        Person person = personService.findPerson(person_id);
        List<Flat> flats = person.getFlatsWithRegistration().stream().distinct().collect(Collectors.toList());
        for (Flat flat: flats) {
            if (flat.getId()==flat_id)
                flats.remove(flat);
        }
        person.setFlatsWithRegistration(flats);
        personService.updatePerson(person);
    }

    public static void registerPersonInTheFlat(int person_id, int flat_id) throws SQLException {
        System.out.println("Регистрация в квартире");
        PersonService personService = new PersonService();
        FlatService flatService = new FlatService();
        Person person = personService.findPerson(person_id);
        Flat flat = flatService.findFlat(flat_id);
        flat.addFlatRegistered(person);
        flatService.updateFlat(flat);
    }

    public static void moveToNewFlat(int newFlat_id, int oldFlat_id) throws SQLException {
        System.out.println("Переезд в новую квартиру");
        FlatService flatService = new FlatService();
        Flat oldFlat = flatService.findFlat(oldFlat_id);
        Flat newFlat = flatService.findFlat(newFlat_id);
        List<Person> personList = oldFlat.getFlatRegistered();
        oldFlat.setFlatRegistered(null);
        newFlat.setFlatRegistered(personList);
        flatService.updateFlat(oldFlat);
        flatService.updateFlat(newFlat);
    }

    public static void changeFlats(int flat1_id, int flat2_id) throws SQLException {
        System.out.println("Обмен квартирами");
        FlatService flatService = new FlatService();
        Flat flat1 = flatService.findFlat(flat1_id);
        Flat flat2 = flatService.findFlat(flat2_id);
        List<Person> personList1 = List.copyOf(flat1.getFlatRegistered());
        List<Person> personList2 = List.copyOf(flat2.getFlatRegistered());
        flat1.setFlatRegistered(personList2);
        flat2.setFlatRegistered(personList1);
        flatService.updateFlat(flat1);
        flatService.updateFlat(flat2);
    }

    public static void main(String[] args) throws SQLException {
        findPeopleRegisteredInGivenFlat(1);
        printDelimiter();
        findPeopleOwningGivenFlat(7);
        printDelimiter();
        findPeopleRegisteredInGivenCity(1);
        printDelimiter();
        findPeopleRegisteredInGivenHouse(4);
        printDelimiter();
        List<Integer> streetList = new ArrayList<Integer>(Arrays.asList(1, 4, 7));
        findPeopleRegisteredOnGivenListOfStreets(streetList);
        printDelimiter();
        deletePersonFromFlat(13, 7);
        printDelimiter();
        registerPersonInTheFlat(13, 7);
        printDelimiter();
        moveToNewFlat(3, 7);
        printDelimiter();
        changeFlats(1, 3);
    }
}

