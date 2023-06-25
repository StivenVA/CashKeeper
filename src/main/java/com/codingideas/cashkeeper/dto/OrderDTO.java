package com.codingideas.cashkeeper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String nombreProveedor;
    private String idProveedor;
    private List<ProductDTO> productos;
    private String fecha;
    private String hora;
    private Long total;
    private int idPedido;
}
