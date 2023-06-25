package com.codingideas.cashkeeper.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalesDTO {
    private Long total;
    private int id_factura;
    private List<ProductDTO> productos;
    private LocalDate fecha;
    private LocalTime hora;
    private String nombreCliente;
    private String id_cliente;
    private String metodo;
}
