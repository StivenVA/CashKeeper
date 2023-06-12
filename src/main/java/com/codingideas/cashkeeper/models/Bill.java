package com.codingideas.cashkeeper.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "factura")
@Data
public class Bill {

    @Id
    @Column(name = "id_factura")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_factura;

    @Column(name = "total")
    private Long total;

    @JoinColumn(name = "id_usuario")
    @ManyToOne(fetch = FetchType.EAGER)
    private User id_usuario;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;
}
