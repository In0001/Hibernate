package services;

import dao.DAOImpl;
import models.Flat;

import java.util.List;

public class FlatService {

    private static DAOImpl flatsDAO;

    public FlatService() {
        flatsDAO = new DAOImpl();
    }

    public Flat findFlat(int id) {
        return flatsDAO.findById(Flat.class, id);
    }

    public void saveFlat(Flat flat) {
        flatsDAO.save(flat);
    }

    public void deleteFlat(Flat flat) {
        flatsDAO.delete(flat);
    }

    public void updateFlat(Flat flat) {
        flatsDAO.update(flat);
    }

    public List<Flat> findAllFlats() {
        return flatsDAO.findAll(Flat.class);
    }
}
