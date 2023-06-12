package com.codingideas.cashkeeper.service;


import com.codingideas.cashkeeper.dto.ProductDTO;
import com.codingideas.cashkeeper.dto.SalesDTO;
import com.codingideas.cashkeeper.interfaces.ISalesService;
import com.codingideas.cashkeeper.models.Bill;
import com.codingideas.cashkeeper.models.Product;
import com.codingideas.cashkeeper.models.Sale;
import com.codingideas.cashkeeper.repository.SaleRepository;
import com.codingideas.cashkeeper.utils.mapper.MapperProductDTO;
import com.codingideas.cashkeeper.utils.mapper.MapperSale;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class SalesService implements ISalesService {

    private final EntityManager entityManager;

    private  final  SaleRepository saleRepository;
    @Override
    public List getSales() {

        List<Bill> listaDeFacturas = entityManager.createQuery("from Bill").getResultList();

        List<ProductDTO> productsDTO = new ArrayList<>();

        List<SalesDTO> salesDTOList= new ArrayList<>();

       for (int i = 0; i < listaDeFacturas.size(); i++) {

           List<Product> productos;
           productos = saleRepository.getProducts(i);

           List<Integer> cantidades;
           cantidades = saleRepository.getAmount(i);

           for (int j = 0; j < productos.size(); j++) {
               MapperProductDTO mapperProductDTO = new MapperProductDTO();
               ProductDTO productDTO = mapperProductDTO.productToProductDTO(productos.get(j),cantidades.get(j));
               productsDTO.add(productDTO);
           }

           MapperSale mapperSale = new MapperSale();
           salesDTOList.add(mapperSale.saleToSaleDTO(listaDeFacturas.get(i),productsDTO));
        }

        return salesDTOList;
    }
}
