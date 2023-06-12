package com.codingideas.cashkeeper.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int cantidad;
    private String id_producto;
    private String descripcion;

}
