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
@Table(name = "institution")
@EqualsAndHashCode(of = "institutionId", callSuper = false)
@ToString(of = "institutionId")
@DynamicInsert
@DynamicUpdate
public class Institution extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "institution_id")
    private Long institutionId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "institution")
    private Set<Donation> donationSet = new HashSet<>();
}
