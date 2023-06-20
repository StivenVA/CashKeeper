package com.codingideas.cashkeeper.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "pedidos")
@Data
@Entity
public class Order {
    @Column(name = "fecha")
    LocalDate fecha;

    @Column(name = "hora")
    LocalTime hora;

    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_pedido;

    @Column(name = "total")
    Long total;

    @JoinColumn(name = "id_proveedor")
    @ManyToOne(fetch = FetchType.EAGER)
    Supplier id_proveedor;

}
