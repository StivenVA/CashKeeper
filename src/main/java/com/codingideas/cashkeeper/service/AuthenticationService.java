package com.codingideas.cashkeeper.service;


import com.codingideas.cashkeeper.interfaces.IAuthentication;
import com.codingideas.cashkeeper.models.AuthRequest;
import com.codingideas.cashkeeper.models.User;
import com.codingideas.cashkeeper.repository.AuthenticationRepository;
import com.codingideas.cashkeeper.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class AuthenticationService implements IAuthentication {

    private final AuthenticationRepository authenticationRepository;

    private  final JWTUtil jwtUtil;

    @Override
    public ResponseEntity<AuthRequest> login(User user) {

        String authUser = authentication(user);

        if (!authUser.equals("OK")) return ResponseEntity.badRequest().body(new AuthRequest(null,null,authUser));

        String token = jwtUtil.create(String.valueOf(user.getEmail()),user.getPassword());

        return ResponseEntity.ok().body(new AuthRequest(token,authenticationRepository.findUserforEmail(user.getEmail()),null));
    }

    public String authentication(User user){
        User email = authenticationRepository.findUserforEmail(user.getEmail());

        if (email==null) return "El correo ingresado no se encuentra asociado a ninguna cuenta";

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashedPassword = email.getPassword();

        return !argon2.verify(hashedPassword,user.getPassword())
                ?"Ha ingresado una contraseña incorrecta pruebe nuevamente"
                :"OK";
    }

}
