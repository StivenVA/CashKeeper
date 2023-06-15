package com.codingideas.cashkeeper.interfaces;

import com.codingideas.cashkeeper.models.Sale;

import java.util.List;

public interface ISalesService {

    List getSales();

    boolean modifySale(Sale sale, boolean auth);

    String registerSale(Sale sale,boolean auth);
}
