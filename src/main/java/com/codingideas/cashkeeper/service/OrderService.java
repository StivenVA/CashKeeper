package com.codingideas.cashkeeper.service;

import com.codingideas.cashkeeper.dto.OrderDTO;
import com.codingideas.cashkeeper.dto.ProductDTO;
import com.codingideas.cashkeeper.interfaces.IOrderService;
import com.codingideas.cashkeeper.models.Order;
import com.codingideas.cashkeeper.models.OrderDetail;
import com.codingideas.cashkeeper.models.Product;
import com.codingideas.cashkeeper.models.Supplier;
import com.codingideas.cashkeeper.repository.OrderRepository;
import com.codingideas.cashkeeper.repository.ProductRespository;
import com.codingideas.cashkeeper.repository.SupplierRepository;
import com.codingideas.cashkeeper.utils.mapper.MapperOrderDTO;
import com.codingideas.cashkeeper.utils.mapper.MapperProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRespository productRespository;

    @Override
    public List getLastOrders(boolean auth) {

        if (!auth) return null;

        List<Order> orderList = orderRepository.getLastOrders();
        List<Long> cantidadProductosPorOrden = orderRepository.getNumberOfProductsForOrder();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        MapperOrderDTO mapperOrderDTO = new MapperOrderDTO();

        for (int i=0 ; i<orderList.size() ; i++) {

            Long cantidad = cantidadProductosPorOrden.get(i);
            List<ProductDTO> productDTOList = new ArrayList<>();
            List<OrderDetail> products = orderRepository.getProductsForOrder(orderList.get(i).getId_pedido());

            for (int j = 0; j < cantidad.intValue(); j++) {
                MapperProductDTO mapperProductDTO = new MapperProductDTO();
                ProductDTO productDTO = mapperProductDTO.productToProductDTO(products.get(j).getId_producto(),products.get(j).getCantidad(),products.get(j).getPrecio_compra());
                productDTOList.add(productDTO);
            }

            orderDTOList.add(mapperOrderDTO.orderToOrderDTO(orderList.get(i),productDTOList));
        }

        return orderDTOList;
    }

    @Override
    public boolean makeOrder(boolean auth, OrderDTO orderDTO) {

        if (!auth) return false;

        MapperOrderDTO mapperOrderDTO = new MapperOrderDTO();

        Supplier supplier = supplierRepository.findSupplier(orderDTO.getIdProveedor());

        orderRepository.mergeOrder(mapperOrderDTO.orderDTOtoOrder(orderDTO,supplier));

        for (ProductDTO productDTO:
             orderDTO.getProductos()) {

           Product product =  productRespository.findProduct(productDTO.getId_producto());
           Order order = orderRepository.findOrder(orderDTO.getIdPedido());

           OrderDetail orderDetail = new OrderDetail();

           orderDetail.setCantidad(productDTO.getCantidad());
           orderDetail.setId_producto(product);
           orderDetail.setPrecio_compra(productDTO.getPrecio());
           orderDetail.setId_pedido(order);

           orderRepository.mergeOrderDetail(orderDetail);
        }

        return true;
    }

}
