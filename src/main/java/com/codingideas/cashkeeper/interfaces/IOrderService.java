package com.codingideas.cashkeeper.interfaces;

import com.codingideas.cashkeeper.dto.OrderDTO;

import java.util.List;

public interface IOrderService {

    List getLastOrders(boolean auth);

    boolean makeOrder(boolean auth, OrderDTO order);
}
