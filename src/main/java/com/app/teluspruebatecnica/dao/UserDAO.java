package com.app.teluspruebatecnica.dao;

import com.app.teluspruebatecnica.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<Users, Long> {
Users findByUsername(String username);
}
