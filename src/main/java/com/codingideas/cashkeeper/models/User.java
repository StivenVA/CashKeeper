package com.codingideas.cashkeeper.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class User {

    @Column(name = "id_usuario") @Id
    private String id;

    @Column(name = "telefono")
    private String telefono;

    @Column(name="password")
    private String password;

    @Column(name = "email") @Id
    private String email;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "rol")
    private int rol;
}
