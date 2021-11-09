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
        housesDAO.openCurrentSession();
        House house = housesDAO.findById(House.class, id);
        housesDAO.closeCurrentSession();
        return house;
    }

    public void saveHouse(House house) {
        housesDAO.openCurrentSession();
        housesDAO.save(house);
        housesDAO.closeCurrentSession();
    }

    public void deleteHouse(House house) {
        housesDAO.openCurrentSession();
        housesDAO.delete(house);
        housesDAO.closeCurrentSession();
    }

    public void updateHouse(House house) {
        housesDAO.openCurrentSession();
        housesDAO.update(house);
        housesDAO.closeCurrentSession();
    }

    public List<House> findAllHouses() {
        housesDAO.openCurrentSession();
        List<House> houses = housesDAO.findAll(House.class);
        housesDAO.closeCurrentSession();
        return houses;
    }
}
