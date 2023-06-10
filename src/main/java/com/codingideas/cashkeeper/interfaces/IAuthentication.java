package com.codingideas.cashkeeper.interfaces;

import com.codingideas.cashkeeper.models.AuthRequest;
import com.codingideas.cashkeeper.models.User;
import org.springframework.http.ResponseEntity;

public interface IAuthentication {

    boolean verifyToken(String token);

    ResponseEntity<AuthRequest> login(User user);

}
