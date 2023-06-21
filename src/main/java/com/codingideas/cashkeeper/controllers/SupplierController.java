package com.codingideas.cashkeeper.controllers;

import com.codingideas.cashkeeper.interfaces.ISupplierService;
import com.codingideas.cashkeeper.models.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/supplier")
@RestController
@RequiredArgsConstructor
public class SupplierController {

    private final ISupplierService iSupplierService;

    @RequestMapping(value = "/get")
    public List getSuppliers(@RequestHeader(value = "Authorization") boolean auth){

        return iSupplierService.getSuppliers(auth);
    }

    @RequestMapping(value = "/add")
    public void addSupplier(@RequestBody Supplier supplier, @RequestHeader boolean auth){
        iSupplierService.addSupplier(supplier, auth);
    }

    @RequestMapping(value = "/edit")
    public void editSupplier(@RequestBody Supplier supplier,@RequestHeader boolean auth){
        iSupplierService.modifySupplier(supplier,auth);
    }

    @RequestMapping(value = "/delete")
    public void removeSupplier(@RequestBody Supplier supplier,@RequestHeader boolean auth){
        iSupplierService.deleteSupplier(supplier, auth);
    }
}
