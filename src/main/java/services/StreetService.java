package services;

import dao.DAOImpl;
import models.Street;

import java.util.List;

public class StreetService {

    private static DAOImpl streetsDAO;

    public StreetService() {
        streetsDAO = new DAOImpl();
    }

    public Street findStreet(int id) {
        return streetsDAO.findById(Street.class, id);
    }

    public void saveStreet(Street street) {
        streetsDAO.save(street);
    }

    public void deleteStreet(Street street) {
        streetsDAO.delete(street);
    }

    public void updateStreet(Street street) {
        streetsDAO.update(street);
    }

    public List<Street> findAllStreets() {
        return streetsDAO.findAll(Street.class);
    }
}
