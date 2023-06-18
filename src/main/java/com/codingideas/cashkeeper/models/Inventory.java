package com.codingideas.cashkeeper.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Table(name = "inventario")
@Entity
@Data
public class Inventory {
    @Id
    @Column(name = "fecha")
    LocalDate fecha;

    @Id
    @JoinColumn(name = "id_producto")
    @ManyToOne(fetch = FetchType.EAGER)
    Product id_producto;

    @Id
    @Column(name = "congelador")
    int congelador;

    @Column(name = "cantidad_disponible")
    int cantidad;
}
