package com.app.teluspruebatecnica.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;


import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "rol")
@EqualsAndHashCode(of = "rolId", callSuper = false)
@ToString(of = "rolId")
@DynamicInsert
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;



}
