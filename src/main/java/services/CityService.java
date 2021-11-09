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
        citiesDAO.openCurrentSession();
        City city = citiesDAO.findById(City.class, id);
        citiesDAO.closeCurrentSession();
        return city;
    }

    public void saveCity(City city) {
        citiesDAO.openCurrentSession();
        citiesDAO.save(city);
        citiesDAO.closeCurrentSession();
    }

    public void deleteCity(City city) {
        citiesDAO.openCurrentSession();
        citiesDAO.delete(city);
        citiesDAO.closeCurrentSession();
    }

    public void updateCity(City city) {
        citiesDAO.openCurrentSession();
        citiesDAO.update(city);
        citiesDAO.closeCurrentSession();
    }

    public List<City> findAllCities() {
        citiesDAO.openCurrentSession();
        List<City> cities = citiesDAO.findAll(City.class);
        citiesDAO.closeCurrentSession();
        return cities;
    }
}