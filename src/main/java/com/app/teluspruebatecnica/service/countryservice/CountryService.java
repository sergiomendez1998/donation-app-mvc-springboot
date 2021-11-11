package com.app.teluspruebatecnica.service.countryservice;

import com.app.teluspruebatecnica.model.Country;

import java.util.List;

public interface CountryService {
    public List<Country> getAllCountries();
    public void addCountry(Country country);
    Country findCountry(Country country);

}
