package com.codingideas.cashkeeper.controllers;


import com.codingideas.cashkeeper.interfaces.ISalesService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
@RequiredArgsConstructor
public class SalesController {

    private final ISalesService iSalesService;

    @RequestMapping(value = "/get")
    public List getSales() {
        return iSalesService.getSales();
    }

}
