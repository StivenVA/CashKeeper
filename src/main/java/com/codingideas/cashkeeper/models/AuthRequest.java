package com.codingideas.cashkeeper.models;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AuthRequest {

    private String token;
    private User user;
    private String error;
}
