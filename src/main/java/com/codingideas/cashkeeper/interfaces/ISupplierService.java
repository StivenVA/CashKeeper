package com.codingideas.cashkeeper.interfaces;

import com.codingideas.cashkeeper.models.Supplier;

import java.util.List;

public interface ISupplierService {

    List getSuppliers();

    void deleteSupplier(Supplier supplier, boolean auth);

    void modifySupplier(Supplier supplierEdited,boolean auth);

    void addSupplier(Supplier supplier,boolean auth);
}
