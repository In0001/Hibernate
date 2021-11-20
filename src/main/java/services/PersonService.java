package services;

import dao.DAOImpl;
import models.Person;

import java.util.List;

public class PersonService {

    private static DAOImpl personsDAO;

    public PersonService() {
        personsDAO = new DAOImpl();
    }

    public Person findPerson(int id) {
        return personsDAO.findById(Person.class, id);
    }

    public void savePerson(Person person) {
        personsDAO.save(person);
    }

    public void deletePerson(Person person) {
        personsDAO.delete(person);
    }

    public void updatePerson(Person person) {
        personsDAO.update(person);
    }

    public List<Person> findAllPersons() {
        return personsDAO.findAll(Person.class);
    }
}
