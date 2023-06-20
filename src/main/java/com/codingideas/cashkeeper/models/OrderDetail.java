package com.codingideas.cashkeeper.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_pedido")
public class OrderDetail {
    @Id
    @JoinColumn(name = "id_producto")
    @ManyToOne(fetch = FetchType.EAGER)
    Product id_producto;

    @Column(name = "cantidad")
    int cantidad;

    @Column(name = "precio_compra")
    Long precio_compra;

    @Id
    @JoinColumn(name = "id_pedido")
    @ManyToOne(fetch = FetchType.EAGER)
    Order id_pedido;
}
