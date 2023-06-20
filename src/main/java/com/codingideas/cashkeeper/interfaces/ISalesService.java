package com.codingideas.cashkeeper.interfaces;

import com.codingideas.cashkeeper.dto.SalesDTO;
import com.codingideas.cashkeeper.models.Sale;

import java.util.List;

public interface ISalesService {

    List getSales(boolean auth);

    boolean modifySale(Sale sale, boolean auth);

    String registerSale(SalesDTO sale, boolean auth);
}
