package com.codingideas.cashkeeper.service;


import com.codingideas.cashkeeper.dto.ProductDTO;
import com.codingideas.cashkeeper.dto.SalesDTO;
import com.codingideas.cashkeeper.interfaces.ISalesService;
import com.codingideas.cashkeeper.models.Bill;
import com.codingideas.cashkeeper.models.Product;
import com.codingideas.cashkeeper.models.Sale;
import com.codingideas.cashkeeper.models.User;
import com.codingideas.cashkeeper.repository.ProductRespository;
import com.codingideas.cashkeeper.repository.SaleRepository;
import com.codingideas.cashkeeper.repository.UserRepository;
import com.codingideas.cashkeeper.utils.mapper.MapperProductDTO;
import com.codingideas.cashkeeper.utils.mapper.MapperSale;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class SalesService implements ISalesService {

    private  final  SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final ProductRespository productRespository;

    @Override
    public List getSales(boolean auth) {

        if (!auth) return null;
        List<Bill> listaDeFacturas = saleRepository.getBills();

        List<Long> cantidadProductosPorFactura = saleRepository.getNumberOfProductsForBill();

        List<SalesDTO> salesDTOList= new ArrayList<>();

       for (int i = 0; i < listaDeFacturas.size(); i++) {

           Long cantidadProductos = cantidadProductosPorFactura.get(i);
           List<ProductDTO> productsDTO = new ArrayList<>();
           List<Sale> productos = saleRepository.getProducts(listaDeFacturas.get(i).getId_factura());

           for (int j = 0; j < cantidadProductos.intValue(); j++) {
               MapperProductDTO mapperProductDTO = new MapperProductDTO();
               ProductDTO productDTO = mapperProductDTO.productToProductDTO(productos.get(j).getId_producto(),productos.get(j).getCantidad(),productos.get(j).getPrecio());
               productsDTO.add(productDTO);
           }

           MapperSale mapperSale = new MapperSale();
           salesDTOList.add(mapperSale.saleToSaleDTO(listaDeFacturas.get(i),productsDTO));
        }

        return salesDTOList;
    }


    @Override
    public String registerSale(SalesDTO saleDTO,boolean auth){

        if (!auth) return "";
        MapperSale mapperSale = new MapperSale();
        User user = userRepository.findUser(saleDTO.getId_cliente());
        Bill bill = mapperSale.saleDTOtoBill(saleDTO,user);
        bill.setId_factura(saleRepository.getLastIdBill());

        saleRepository.mergeBill(bill);

        for (ProductDTO productDTO: saleDTO.getProductos()) {

            Sale sale = new Sale();
            Product product = productRespository.findProduct(productDTO.getId_producto());

            sale.setId_producto(product);
            sale.setId_factura(bill);
            sale.setCantidad(productDTO.getCantidad());
            sale.setPrecio(product.getPrecio());

            saleRepository.mergeSale(sale);
        }

        return "OK";
    }
}
