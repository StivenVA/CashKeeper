package com.codingideas.cashkeeper.utils.mapper;

import com.codingideas.cashkeeper.dto.OrderDTO;
import com.codingideas.cashkeeper.models.Order;

public class MapperOrderDTO {

    public OrderDTO orderToOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setHora(order.getHora());
        orderDTO.setFecha(order.getFecha());
        orderDTO.setCantidadProducto(order.getCantidad());
        orderDTO.setIdProveedor(order.getId_proveedor().getId_proveedor());
        orderDTO.setDescripcionProducto(order.getId_producto().getDescripcion());
        orderDTO.setNombreProveedor(order.getId_proveedor().getNombre());

        return orderDTO;
    }

}
