package com.codingideas.cashkeeper.controllers;

import com.codingideas.cashkeeper.interfaces.IProductService;
import com.codingideas.cashkeeper.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;

    @RequestMapping(value = "/get")
    public List getProducts(@RequestHeader(value = "Authorization") boolean auth){
    return iProductService.getProduct(auth);
    }

    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public String modifyProduct(@RequestBody Product productEdited, @RequestHeader(value = "Authorization") boolean auth){
        return iProductService.modifyProduct(productEdited,auth);
    }

    @RequestMapping(value = "/add")
    public String addProduct(@RequestBody Product product,@RequestHeader(value = "Authorization")boolean auth ){
        return  iProductService.addProduct(product,auth);
    }

    @RequestMapping(value = "/delete")
    public boolean deleteProduct(@RequestBody String id_product,@RequestHeader(value = "Authorization") boolean auth){
        return iProductService.deleteProduct(id_product,auth);
    }

}
