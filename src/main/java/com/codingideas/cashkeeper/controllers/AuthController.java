package com.codingideas.cashkeeper.controllers;

import com.codingideas.cashkeeper.interfaces.IAuthentication;
import com.codingideas.cashkeeper.models.AuthRequest;
import com.codingideas.cashkeeper.models.User;
import com.codingideas.cashkeeper.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final IAuthentication iAuthentication;

    private final JWTUtil jwtUtil;

    @RequestMapping(value = "/log",method = RequestMethod.POST)
    public ResponseEntity<AuthRequest> login(@RequestBody User user) {

        return  iAuthentication.login(user);
    }

    @RequestMapping(value = "/token")
    public boolean verifyToken(@RequestHeader(value = "Authorization") String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId!=null;
    }


}
