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
        flatsDAO.openCurrentSession();
        Flat flat = flatsDAO.findById(Flat.class, id);
        flatsDAO.closeCurrentSession();
        return flat;
    }

    public void saveFlat(Flat flat) {
        flatsDAO.openCurrentSession();
        flatsDAO.save(flat);
        flatsDAO.closeCurrentSession();
    }

    public void deleteFlat(Flat flat) {
        flatsDAO.openCurrentSession();
        flatsDAO.delete(flat);
        flatsDAO.closeCurrentSession();
    }

    public void updateFlat(Flat flat) {
        flatsDAO.openCurrentSession();
        flatsDAO.update(flat);
        flatsDAO.closeCurrentSession();
    }

    public List<Flat> findAllFlats() {
        flatsDAO.openCurrentSession();
        List<Flat> flats = flatsDAO.findAll(Flat.class);
        flatsDAO.closeCurrentSession();
        return flats;
    }
}
