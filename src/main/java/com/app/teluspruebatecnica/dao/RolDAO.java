package com.app.teluspruebatecnica.dao;

import com.app.teluspruebatecnica.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDAO extends JpaRepository<Rol, Long> {
}
