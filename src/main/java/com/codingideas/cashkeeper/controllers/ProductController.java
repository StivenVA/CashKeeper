package com.codingideas.cashkeeper.controllers;

import com.codingideas.cashkeeper.interfaces.IProductService;
import com.codingideas.cashkeeper.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {

    @PersistenceContext
    private final EntityManager entityManager;

    private final IProductService iProductService;

    @RequestMapping(value = "/get")
    public List getProducts(/*@RequestHeader(value = "Authorization") boolean auth*/){
    return iProductService.getProduct(/*auth*/true);
    }

    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public String modifyProduct(@RequestBody Product productEdited, @RequestHeader(value = "Authorization") boolean auth){
        return iProductService.modifyProduct(productEdited,auth);
    }

    @RequestMapping(value = "/add")
    public boolean addProduct(@RequestBody Product product,@RequestHeader(value = "Authorization")boolean auth ){
        return  iProductService.addProduct(product,auth);
    }

}
