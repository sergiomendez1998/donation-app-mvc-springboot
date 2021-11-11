package com.app.teluspruebatecnica.service.donationservice;


import com.app.teluspruebatecnica.model.Country;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DonationWrapper {

    private Long donationId;
    private Double donationAmount;
    private LocalDateTime dateOfDonation;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Users user;
//

    private Country country;
//
//    @ManyToOne
//    @JoinColumn(name = "institution_id")
//    private Institution institution;


    public DonationWrapper(Long donationId, Double donationAmount, LocalDateTime dateOfDonation, Country country) {
        this.donationId = donationId;
        this.donationAmount = donationAmount;
        this.dateOfDonation = dateOfDonation;
        this.country = country;
    }
}
