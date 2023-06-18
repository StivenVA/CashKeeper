package com.codingideas.cashkeeper.interfaces;

import java.util.List;

public interface IOrderService {

    List getLastOrders(boolean auth);
}
