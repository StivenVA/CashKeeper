package com.codingideas.cashkeeper.repository;


import com.codingideas.cashkeeper.models.Order;
import com.codingideas.cashkeeper.models.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class OrderRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<OrderDetail> getProductsForOrder(int id_pedido){
        return entityManager.createQuery("select or from (select max(fecha) as maxfecha from Order) f,Order o,OrderDetail or where f.maxfecha = o.fecha and or.id_pedido.id_pedido="+id_pedido).getResultList();
    }

    public List<Integer> getIdOrder(){
        return entityManager.createQuery("select id_pedido from (select max(fecha) as maxfecha from Order) f,Order o where o.fecha=f.maxfecha").getResultList();
    }

    public List<Long> getNumberOfProductsForOrder(){
        return entityManager.createQuery("SELECT COUNT(d.id_producto) FROM Order o JOIN OrderDetail d ON o.id_pedido = d.id_pedido.id_pedido WHERE o.fecha = (SELECT MAX(fecha) FROM Order ) GROUP BY o.id_pedido").getResultList();
    }

    public List<Order> getLastOrders(){
        return entityManager.createQuery("select o from (select max(fecha) as maxfecha from Order) f,Order o where f.maxfecha = o.fecha").getResultList();
    }

    public List<OrderDetail> getProductsForLastOrder(){
        return entityManager.createQuery("select od from (select max(fecha) as maxfecha from Order) f,Order o,OrderDetail od where f.maxfecha = o.fecha and od.id_pedido.id_pedido = o.id_pedido").getResultList();
    }

    public void mergeOrder(Order order){
        entityManager.persist(order);
    }

    public void mergeOrderDetail(OrderDetail orderDetail){
        entityManager.persist(orderDetail);
    }

    public Order findOrder(int id){
        return entityManager.find(Order.class,id);
    }

    public int getNextOrder(){

        List<Integer> next = entityManager.createQuery("select max(id_pedido) from Order ").getResultList();
        return next.get(0);
    }
}
