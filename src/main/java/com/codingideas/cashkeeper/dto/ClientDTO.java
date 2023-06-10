package com.codingideas.cashkeeper.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private String id;
    private String nombre;
    private String direccion;
    private String telefono;
}
