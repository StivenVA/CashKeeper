package com.codingideas.cashkeeper.service;


import com.codingideas.cashkeeper.interfaces.IProductService;
import com.codingideas.cashkeeper.models.Product;
import com.codingideas.cashkeeper.repository.ProductRespository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Repository
public class ProductService implements IProductService {

    private final ProductRespository productRespository;

    @Override
    public List getProduct(boolean auth) {

        if (!auth) return null;
        return productRespository.getProducts();
    }

    @Override
    public String modifyProduct(Product productEdited, boolean auth) {

        if (!auth) return "Por favor inicie sesion nuevamente";

        Product product = productRespository.findProduct(productEdited.getId());

        product.setDescripcion(productEdited.getDescripcion());
        product.setPrecio(productEdited.getPrecio());

        productRespository.mergeProduct(product);

        return "OK";
    }

    @Override
    public String addProduct(Product product, boolean auth) {

        if (!auth) return "Por favor inicie sesion nuevamente";

        if(productRespository.findProduct(product.getId())!=null) return "Ya existe un producto con esa identificacion";

        productRespository.mergeProduct(product);

        return "OK";
    }

    @Override
    public boolean deleteProduct(String id, boolean auth) {

        if (!auth) return false;

        Product product = productRespository.findProduct(id);

        product.setEstado(2);

        productRespository.mergeProduct(product);

        return true;
    }

}
