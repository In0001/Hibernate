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
        personsDAO.openCurrentSession();
        Person person = personsDAO.findById(Person.class, id);
        personsDAO.closeCurrentSession();
        return person;
    }

    public void savePerson(Person person) {
        personsDAO.openCurrentSession();
        personsDAO.save( person);
        personsDAO.closeCurrentSession();
    }

    public void deletePerson(Person person) {
        personsDAO.openCurrentSession();
        personsDAO.delete(person);
        personsDAO.closeCurrentSession();
    }

    public void updatePerson(Person person) {
        personsDAO.openCurrentSession();
        personsDAO.update(person);
        personsDAO.closeCurrentSession();
    }

    public List<Person> findAllPersons() {
        personsDAO.openCurrentSession();
        List<Person> persons = personsDAO.findAll(Person.class);
        personsDAO.closeCurrentSession();
        return persons;
    }
}
