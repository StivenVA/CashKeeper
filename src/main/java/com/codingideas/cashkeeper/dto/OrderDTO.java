package com.codingideas.cashkeeper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String nombreProveedor;
    private String idProveedor;
    private String descripcionProducto;
    private int cantidadProducto;
    private LocalDate fecha;
    private LocalTime hora;
}
