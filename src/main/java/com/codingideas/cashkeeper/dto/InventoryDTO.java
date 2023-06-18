package com.codingideas.cashkeeper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    private LocalDate fecha;
    private String id_producto;
    private String descripcion;
    private int congelador;
    private int cantidad;
}
