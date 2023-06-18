package com.codingideas.cashkeeper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalInventoryDTO{
    private String id_producto;
    private String descripcion;
    private int cantidad;
    private LocalDate fecha;
}
