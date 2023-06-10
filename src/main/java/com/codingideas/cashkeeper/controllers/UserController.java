package com.codingideas.cashkeeper.controllers;

import com.codingideas.cashkeeper.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    @PersistenceContext
    private final EntityManager entityManager;

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public String signUpUser(@RequestBody @NotNull User user){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        String hashedPassword = argon2.hash(3,2,1024,user.getPassword());
        user.setPassword(hashedPassword);

        User id = entityManager.find(User.class,user.getId());

        List email = entityManager.createQuery("FROM User where email='"+user.getEmail()+"'").getResultList();

        if (id != null) return "Ya existe una cuenta asociada a este numero de identidad";
        if (!email.isEmpty()) return "Ya existe una cuenta asociada a este correo";

        entityManager.merge(user);

        return "Registro exitoso";
    }

    @RequestMapping(value = "/get/users",method = RequestMethod.GET)
    public List getUsers(){

        return entityManager.createQuery("FROM User").getResultList();
    };



}
