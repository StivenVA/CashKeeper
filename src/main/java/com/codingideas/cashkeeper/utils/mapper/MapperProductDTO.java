package com.codingideas.cashkeeper.utils.mapper;

import com.codingideas.cashkeeper.dto.ProductDTO;
import com.codingideas.cashkeeper.models.Product;

public class MapperProductDTO {

    public ProductDTO productToProductDTO(Product product,int cantidad){

        ProductDTO productDTO = new ProductDTO();

        productDTO.setCantidad(cantidad);
        productDTO.setDescripcion(product.getDescripcion());
        productDTO.setId_producto(product.getId());

        return productDTO;
    }

}
