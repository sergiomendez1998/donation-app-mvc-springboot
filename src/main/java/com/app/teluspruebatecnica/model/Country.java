package com.app.teluspruebatecnica.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "country")
@EqualsAndHashCode(of = "countryId", callSuper = false)
@ToString(of = "countryId")
@DynamicInsert
@DynamicUpdate
public class Country extends BaseEntity  {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Donation> donationSet = new HashSet<>();

}
