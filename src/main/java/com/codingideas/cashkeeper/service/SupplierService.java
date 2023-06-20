package com.codingideas.cashkeeper.service;

import com.codingideas.cashkeeper.interfaces.ISupplierService;
import com.codingideas.cashkeeper.models.Supplier;
import com.codingideas.cashkeeper.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Repository
public class SupplierService implements ISupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public List getSuppliers(boolean auth) {
        if (!auth) return null;
        return supplierRepository.getSuppliersRepository();
    }

    @Override
    public void deleteSupplier(Supplier supplier, boolean auth) {
        if(!auth) return;

        Supplier supplierfinded =  supplierRepository.findSupplier(supplier.getId_proveedor());
        supplierRepository.removeSupplier(supplierfinded);
    }

    @Override
    public void modifySupplier(Supplier supplierEdited, boolean auth) {
        if (!auth) return;

        Supplier supplier = supplierRepository.findSupplier(supplierEdited.getId_proveedor());

        supplier.setEmail(supplierEdited.getEmail());
        supplier.setTelefono(supplierEdited.getTelefono());

        supplierRepository.mergeSupplier(supplier);
    }

    @Override
    public void addSupplier(Supplier supplier, boolean auth) {
        if (!auth) return;
        supplierRepository.mergeSupplier(supplier);
    }
}
