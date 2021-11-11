package com.app.teluspruebatecnica.dao;

import com.app.teluspruebatecnica.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionDAO extends JpaRepository<Institution, Long> {
}
