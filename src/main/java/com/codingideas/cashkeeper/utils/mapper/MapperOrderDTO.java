package com.codingideas.cashkeeper.utils.mapper;

import com.codingideas.cashkeeper.dto.OrderDTO;
import com.codingideas.cashkeeper.dto.ProductDTO;
import com.codingideas.cashkeeper.models.Order;

import java.util.List;

public class MapperOrderDTO {

    public OrderDTO orderToOrderDTO(Order order, List<ProductDTO> productos){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setHora(order.getHora());
        orderDTO.setFecha(order.getFecha());
        orderDTO.setIdProveedor(order.getId_proveedor().getId_proveedor());
        orderDTO.setNombreProveedor(order.getId_proveedor().getNombre());
        orderDTO.setProductos(productos);

        return orderDTO;
    }

}
