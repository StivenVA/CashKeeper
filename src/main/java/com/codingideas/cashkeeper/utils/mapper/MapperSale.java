package com.codingideas.cashkeeper.utils.mapper;

import com.codingideas.cashkeeper.dto.ProductDTO;
import com.codingideas.cashkeeper.dto.SalesDTO;
import com.codingideas.cashkeeper.models.Bill;
import com.codingideas.cashkeeper.models.User;
import com.codingideas.cashkeeper.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
public class MapperSale {

    public SalesDTO saleToSaleDTO(Bill factura, List<ProductDTO> productos){

        SalesDTO salesDTO = new SalesDTO();

        salesDTO.setProducts(productos);
        salesDTO.setHora(factura.getHora());
        salesDTO.setFecha(factura.getFecha());
        salesDTO.setId_factura(factura.getId_factura());
        salesDTO.setTotal(factura.getTotal());
        salesDTO.setNombreCliente(factura.getId_usuario().getNombre());
        salesDTO.setId_cliente(factura.getId_usuario().getId());


        return salesDTO;
    }

    public Bill saleDTOtoBill(SalesDTO saleDTO,User user){
        Bill bill = new Bill();

        bill.setHora(LocalTime.now());
        bill.setFecha(LocalDate.now());
        bill.setId_usuario(user);
        bill.setTotal(saleDTO.getTotal());
        bill.setId_factura(saleDTO.getId_factura());

        return bill;
    }
}
