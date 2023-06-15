package com.codingideas.cashkeeper.interfaces;

import com.codingideas.cashkeeper.models.User;

import java.util.List;

public interface IUserService {

    boolean ediClient(User userEdited, String auth);

    List getClients();

    String addClient(User user,String auth);

    boolean removeClient(String id,String auth);

    String addUser(User user);
}
