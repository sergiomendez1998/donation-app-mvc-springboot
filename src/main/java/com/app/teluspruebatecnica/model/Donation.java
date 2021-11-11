package com.app.teluspruebatecnica.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "donation")
@EqualsAndHashCode(of = "donationId", callSuper = false)
@ToString(of = "donationId")
@DynamicInsert
@DynamicUpdate
public class Donation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Long donationId;

    @Column(name = "donation_amount")
    private Double donationAmount;

    @Column(name = "date_of_donation")
    private LocalDateTime dateOfDonation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @PrePersist
    public void prePersist() {
      this.dateOfDonation = LocalDateTime.now();
    }
}
