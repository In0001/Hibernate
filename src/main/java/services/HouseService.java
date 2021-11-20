package services;

import dao.DAOImpl;
import models.House;

import java.util.List;

public class HouseService {

    private static DAOImpl housesDAO;

    public HouseService() {
        housesDAO = new DAOImpl();
    }

    public House findHouse(int id) {
        return housesDAO.findById(House.class, id);
    }

    public void saveHouse(House house) {
        housesDAO.save(house);
    }

    public void deleteHouse(House house) {
        housesDAO.delete(house);
    }

    public void updateHouse(House house) {
        housesDAO.update(house);
    }

    public List<House> findAllHouses() {
        return housesDAO.findAll(House.class);
    }
}
