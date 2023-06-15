package com.codingideas.cashkeeper.controllers;


import com.codingideas.cashkeeper.interfaces.ISalesService;
import com.codingideas.cashkeeper.models.Sale;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="edit",method = RequestMethod.POST)
    public boolean editSale(@RequestBody Sale sale, @RequestHeader(value = "Authorization") boolean auth){
        return iSalesService.modifySale(sale,auth);
    }

    @RequestMapping(value = "/add")
    public String registerSale(@RequestBody Sale sale,@RequestHeader(value = "Authorization") boolean auth){
        return  iSalesService.registerSale(sale,auth);
    }
}
