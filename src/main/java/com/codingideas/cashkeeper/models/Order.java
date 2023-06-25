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
    String fecha;

    @Column(name = "hora")
    String hora;

    @Id
    @Column(name = "id_pedido")
    int id_pedido;

    @Column(name = "total")
    Long total;

    @JoinColumn(name = "id_proveedor")
    @ManyToOne(fetch = FetchType.EAGER)
    Supplier id_proveedor;

}
