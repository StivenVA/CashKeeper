package com.codingideas.cashkeeper.controllers;

import com.codingideas.cashkeeper.models.AuthRequest;
import com.codingideas.cashkeeper.models.User;
import com.codingideas.cashkeeper.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final JWTUtil jwtUtil;

    private final EntityManager entityManager;

    public String authentication(User user){
        List<User> email = entityManager
                .createQuery("FROM User where email='"+user.getEmail()+"'").getResultList();
        if (email.isEmpty()) return "El correo ingresado no se encuentra asociado a ninguna cuenta";

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashedPassword = email.get(0).getPassword();

        return !argon2.verify(hashedPassword,user.getPassword())
                ?"Ha ingresado una contraseña incorrecta pruebe nuevamente"
                :"OK";
    }

    public User findUser(User user){
        List<User> email = entityManager
                .createQuery("FROM User where email='"+user.getEmail()+"'").getResultList();
        return entityManager.find(User.class,email.get(0).getId());
    }


    @RequestMapping(value = "/log",method = RequestMethod.POST)
    public ResponseEntity<AuthRequest> iniciarSesion(@RequestBody User user) {

        if (!authentication(user).equals("OK")) return ResponseEntity.badRequest().body(new AuthRequest(null,null,authentication(user)));

        String token = jwtUtil.create(String.valueOf(user.getEmail()),user.getPassword());

        return ResponseEntity.ok().body(new AuthRequest(token,findUser(user),null));
    }

    @RequestMapping(value="/token")
    public boolean checkToken(@RequestHeader(value = "Authorization") String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId!=null;
    }
}
