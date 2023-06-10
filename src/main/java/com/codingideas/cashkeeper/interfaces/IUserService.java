package com.codingideas.cashkeeper.interfaces;

import com.codingideas.cashkeeper.models.User;

import java.util.List;

public interface IUserService {

    boolean ediClient(User userEdited, boolean auth);

    List getClients();

    String addClient(User user,boolean auth);

    boolean removeClient(String id,boolean auth);

}
