package com.codingideas.cashkeeper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String nombreProveedor;
    private String idProveedor;
    private List<ProductDTO> productos;
    private LocalDate fecha;
    private LocalTime hora;
    private Long total;
    private int idPedido;
}
