package com.codingideas.cashkeeper.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "producto")
@Data
public class Product {

    @Id
    @Column(name = "id_producto")
    private String id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Long precio;

    @Column(name = "estado")
    private int estado;
}
