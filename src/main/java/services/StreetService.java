package services;

import dao.DAOImpl;
import models.Person;
import models.Street;

import java.util.List;

public class StreetService {

    private static DAOImpl streetsDAO;

    public StreetService() {
        streetsDAO = new DAOImpl();
    }

    public Street findStreet(int id) {
        streetsDAO.openCurrentSession();
        Street street = streetsDAO.findById(Street.class, id);
        streetsDAO.closeCurrentSession();
        return street;
    }

    public void saveStreet(Street street) {
        streetsDAO.openCurrentSession();
        streetsDAO.save(street);
        streetsDAO.closeCurrentSession();
    }

    public void deleteStreet(Street street) {
        streetsDAO.openCurrentSession();
        streetsDAO.delete(street);
        streetsDAO.closeCurrentSession();
    }

    public void updateStreet(Street street) {
        streetsDAO.openCurrentSession();
        streetsDAO.update(street);
        streetsDAO.closeCurrentSession();
    }

    public List<Street> findAllStreets() {
        streetsDAO.openCurrentSession();
        List<Street> streets = streetsDAO.findAll(Street.class);
        streetsDAO.closeCurrentSession();
        return streets;
    }
}
