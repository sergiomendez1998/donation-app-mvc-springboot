package com.app.teluspruebatecnica.service.countryservice;

import com.app.teluspruebatecnica.dao.CountryDAO;
import com.app.teluspruebatecnica.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDAO countryDAO;


    //get all countries
    @Override
    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return (List<Country>) countryDAO.findAll();
    }

    //save the country method
    @Override
    @Transactional
    public void addCountry(Country country) {
         countryDAO.save(country);
    }



    //get the country by id
    @Override
    @Transactional(readOnly = true)
    public Country findCountry(Country country) {
        return countryDAO.findById(country.getCountryId()).orElse(null);
    }
}
