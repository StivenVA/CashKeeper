package com.codingideas.cashkeeper.utils.mapper;

import com.codingideas.cashkeeper.dto.OrderDTO;
import com.codingideas.cashkeeper.dto.ProductDTO;
import com.codingideas.cashkeeper.models.Order;
import com.codingideas.cashkeeper.models.Supplier;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MapperOrderDTO {

    public OrderDTO orderToOrderDTO(Order order, List<ProductDTO> productos){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setHora(order.getHora());
        orderDTO.setFecha(order.getFecha());
        orderDTO.setIdProveedor(order.getId_proveedor().getId_proveedor());
        orderDTO.setNombreProveedor(order.getId_proveedor().getNombre());
        orderDTO.setProductos(productos);
        orderDTO.setTotal(order.getTotal());
        orderDTO.setIdPedido(order.getId_pedido());

        return orderDTO;
    }

    public Order orderDTOtoOrder(OrderDTO orderDTO, Supplier proveedor){

        Order order = new Order();

        order.setFecha(LocalDate.now());
        order.setHora(LocalTime.now());
        order.setTotal(orderDTO.getTotal());
        order.setId_proveedor(proveedor);
        order.setId_pedido(orderDTO.getIdPedido());
        
        return order;
    }

}
