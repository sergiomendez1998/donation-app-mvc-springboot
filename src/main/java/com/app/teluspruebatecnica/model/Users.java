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
@Table(name = "users")
@EqualsAndHashCode(of = "userId", callSuper = false)
@ToString(of = "userId")
@DynamicInsert
@DynamicUpdate
public class Users extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "id_document")
    private int idDocument;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy ="user")
    private Set<Rol> rolSet = new HashSet<>();


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Donation> donationSet = new HashSet<>();

}
