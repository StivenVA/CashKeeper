package com.codingideas.cashkeeper.service;


import com.codingideas.cashkeeper.dto.ProductDTO;
import com.codingideas.cashkeeper.dto.SalesDTO;
import com.codingideas.cashkeeper.interfaces.ISalesService;
import com.codingideas.cashkeeper.models.Bill;
import com.codingideas.cashkeeper.models.Product;
import com.codingideas.cashkeeper.models.Sale;
import com.codingideas.cashkeeper.repository.ProductRespository;
import com.codingideas.cashkeeper.repository.SaleRepository;
import com.codingideas.cashkeeper.utils.mapper.MapperProductDTO;
import com.codingideas.cashkeeper.utils.mapper.MapperSale;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class SalesService implements ISalesService {

    private  final  SaleRepository saleRepository;
    private final ProductRespository productRespository;

    @Override
    public List getSales() {

        List<Bill> listaDeFacturas = saleRepository.getBills();

        List<Long> cantidadProductosPorFactura = saleRepository.getNumberOfProductsForBill();

        List<SalesDTO> salesDTOList= new ArrayList<>();

        List<Sale> productos = saleRepository.getProducts();

       for (int i = 0; i < listaDeFacturas.size(); i++) {

           Long cantidadProductos = cantidadProductosPorFactura.get(i);
           List<ProductDTO> productsDTO = new ArrayList<>();

           for (int j = 0; j < cantidadProductos.intValue(); j++) {
               MapperProductDTO mapperProductDTO = new MapperProductDTO();
               ProductDTO productDTO = mapperProductDTO.productToProductDTO(productos.get(j).getId_producto(),productos.get(j).getCantidad());
               productsDTO.add(productDTO);
           }

           MapperSale mapperSale = new MapperSale();
           salesDTOList.add(mapperSale.saleToSaleDTO(listaDeFacturas.get(i),productsDTO));
        }

        return salesDTOList;
    }

    @Override
    public boolean modifySale(Sale sale, boolean auth) {

        if(!auth) return false;

        saleRepository.updateSale(sale);
        saleRepository.updateBill(sale);

        return true;
    }

    @Override
    public String registerSale(Sale sale,boolean auth){

        if (!auth) return "";

        LocalTime hora = LocalTime.now();
        LocalDate fecha = LocalDate.now();

        Bill bill = new Bill();
        bill.setHora(hora);
        bill.setFecha(fecha);
        bill.setTotal(sale.getId_factura().getTotal());

        saleRepository.addSale(sale,bill);
        return "OK";
    }
}
