package com.codingideas.cashkeeper.service;

import com.codingideas.cashkeeper.interfaces.IOrderService;
import com.codingideas.cashkeeper.repository.OrderRepository;
import com.codingideas.cashkeeper.utils.mapper.MapperOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    @Override
    public List getLastOrders(boolean auth) {

        if (!auth) return null;

        MapperOrderDTO mapperOrderDTO = new MapperOrderDTO();

        return orderRepository.getLastOrders()
                .stream().map(mapperOrderDTO::orderToOrderDTO).collect(Collectors.toList());
    }
}
