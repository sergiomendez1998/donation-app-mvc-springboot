package com.app.teluspruebatecnica.service.donationservice;

import com.app.teluspruebatecnica.model.Country;
import com.app.teluspruebatecnica.model.Donation;
import com.app.teluspruebatecnica.model.Users;

import java.util.List;

public interface DonationService {

    public List<Donation> getDonationsByUser(Users user);
    public void saveDonation(Donation donation);
    Donation getDonationByCountry(Country country);
}
