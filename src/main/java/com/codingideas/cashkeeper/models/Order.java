package com.codingideas.cashkeeper.models;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "pedidos")
@Data
@Entity
public class Order {
    @Id
    @Column(name = "fecha")
    LocalDate fecha;

    @Id
    @Column(name = "hora")
    LocalTime hora;

    @Column(name = "cantidad")
    int cantidad;

    @Id
    @JoinColumn(name = "id_producto")
    @ManyToOne(fetch = FetchType.EAGER)
    Product id_producto;

    @Id
    @JoinColumn(name = "id_proveedor")
    @ManyToOne(fetch = FetchType.EAGER)
    Supplier id_proveedor;

}
