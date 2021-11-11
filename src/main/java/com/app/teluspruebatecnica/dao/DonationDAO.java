package com.app.teluspruebatecnica.dao;

import com.app.teluspruebatecnica.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationDAO extends JpaRepository<Donation, Long> {
}
