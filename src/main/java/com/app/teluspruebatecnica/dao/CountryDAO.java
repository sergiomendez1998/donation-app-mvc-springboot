package com.app.teluspruebatecnica.dao;

import com.app.teluspruebatecnica.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDAO extends JpaRepository<Country, Long> {
}
