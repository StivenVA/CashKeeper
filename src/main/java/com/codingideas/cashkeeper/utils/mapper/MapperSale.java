package com.codingideas.cashkeeper.utils.mapper;

import com.codingideas.cashkeeper.dto.ProductDTO;
import com.codingideas.cashkeeper.dto.SalesDTO;
import com.codingideas.cashkeeper.models.Bill;
import com.codingideas.cashkeeper.models.Sale;

import java.util.List;

public class MapperSale {

    public SalesDTO saleToSaleDTO(Bill factura, List<ProductDTO> productos){

        SalesDTO salesDTO = new SalesDTO();

        salesDTO.setProducts(productos);
        salesDTO.setHora(factura.getHora());
        salesDTO.setFecha(factura.getFecha());
        salesDTO.setId_factura(factura.getId_factura());
        salesDTO.setTotal(factura.getTotal());
        salesDTO.setNombre(factura.getId_usuario().getNombre());

        return salesDTO;
    }
}
