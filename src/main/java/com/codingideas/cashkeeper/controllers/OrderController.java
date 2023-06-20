package com.codingideas.cashkeeper.controllers;

import com.codingideas.cashkeeper.dto.OrderDTO;
import com.codingideas.cashkeeper.interfaces.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService iOrderService;

    @RequestMapping(value = "/get")
    public List getOrders(@RequestHeader(value = "Authorization") boolean auth){
        return iOrderService.getLastOrders(auth);
    }

    @RequestMapping(value = "/add")
    public boolean makeOrder(@RequestHeader(value = "Authorization") boolean auth, @RequestBody OrderDTO order){
        return iOrderService.makeOrder(auth,order);
    }
}
