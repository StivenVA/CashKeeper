package com.codingideas.cashkeeper.interfaces;

import com.codingideas.cashkeeper.models.Product;

import java.util.List;

public interface IProductService {

    List getProduct(boolean auth);

    String modifyProduct(Product productEdited,boolean auth);

    boolean addProduct(Product product,boolean auth);
}
