package com.codingideas.cashkeeper.service;

import com.codingideas.cashkeeper.dto.OrderDTO;
import com.codingideas.cashkeeper.dto.ProductDTO;
import com.codingideas.cashkeeper.interfaces.IOrderService;
import com.codingideas.cashkeeper.models.Order;
import com.codingideas.cashkeeper.models.Product;
import com.codingideas.cashkeeper.repository.OrderRepository;
import com.codingideas.cashkeeper.utils.mapper.MapperOrderDTO;
import com.codingideas.cashkeeper.utils.mapper.MapperProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    @Override
    public List getLastOrders(boolean auth) {

        if (!auth) return null;

        List<Long> cantidadProductosPorOrden = orderRepository.getNumberOfProductsForSupplier();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        List<String> nombreProveedores = orderRepository.getSuppliersNameForLastOrders();
        System.out.println(nombreProveedores.get(0));
        MapperOrderDTO mapperOrderDTO = new MapperOrderDTO();

        for (int i=0 ; i<cantidadProductosPorOrden.size() ; i++) {

            Long cantidad = cantidadProductosPorOrden.get(i);
            List<ProductDTO> productDTOList = new ArrayList<>();
            List<Order> orders = orderRepository.getLastOrderForSupplier(nombreProveedores.get(i));

            for (int j = 0; j < cantidad.intValue(); j++) {
                MapperProductDTO mapperProductDTO = new MapperProductDTO();
                ProductDTO productDTO = mapperProductDTO.productToProductDTO(orders.get(j).getId_producto(),orders.get(j).getCantidad());
                productDTOList.add(productDTO);
            }

            orderDTOList.add(mapperOrderDTO.orderToOrderDTO(orders.get(i),productDTOList));
        }

        return orderDTOList;
    }

    public boolean makeOrder(){
        return false;
    }
}
