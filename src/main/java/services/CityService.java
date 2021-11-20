package services;

import dao.DAOImpl;
import models.City;

import java.util.List;

public class CityService {

    private static DAOImpl citiesDAO;

    public CityService() {
        citiesDAO = new DAOImpl();
    }

    public City findCity(int id) {
        return citiesDAO.findById(City.class, id);
    }

    public void saveCity(City city) {
        citiesDAO.save(city);
    }

    public void deleteCity(City city) {
        citiesDAO.delete(city);
    }

    public void updateCity(City city) {
        citiesDAO.update(city);
    }

    public List<City> findAllCities() {
        return citiesDAO.findAll(City.class);
    }
}