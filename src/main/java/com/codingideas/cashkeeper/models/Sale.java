package com.codingideas.cashkeeper.models;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "venta")
public class Sale {

    @Id
    @JoinColumn(name="id_producto")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product id_producto;

    @Id
    @JoinColumn(name = "id_factura")
    @ManyToOne(fetch = FetchType.EAGER)
    private Bill id_factura;

    @Column(name="cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private Long precio;
}
