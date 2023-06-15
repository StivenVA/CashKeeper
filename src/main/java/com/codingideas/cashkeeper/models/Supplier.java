package com.codingideas.cashkeeper.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedores")
public class Supplier {

    @Id @Column(name = "id_proveedor")
    String id_proveedor;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "telefono")
    String telefono;

    @Column(name = "email")
    String email;
}
