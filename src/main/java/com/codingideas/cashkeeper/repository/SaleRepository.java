package com.codingideas.cashkeeper.repository;

import com.codingideas.cashkeeper.models.Bill;
import com.codingideas.cashkeeper.models.Sale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class SaleRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Sale> getProducts(int id){
        return entityManager.createQuery("select s from (select max(fecha) as maxfecha from Bill) f,Sale s,Bill b where f.maxfecha = b.fecha and s.id_factura.id_factura="+id).getResultList();
    }

    public List<Long> getNumberOfProductsForBill(){
        return entityManager
                .createQuery("SELECT COUNT(s.id_factura) FROM Bill b JOIN Sale s ON b.id_factura = s.id_factura.id_factura WHERE b.fecha = (SELECT MAX(fecha) FROM Bill ) GROUP BY b.id_factura").getResultList();
    }

    public List<Bill> getBills(){
        return entityManager.createQuery("from Bill where fecha = (select max(fecha) from Bill)").getResultList();
    }


    public void mergeBill(Bill bill){
        entityManager.merge(bill);
    }

    public int getLastIdBill(){
        List<Integer> lastId = entityManager.createQuery("select max(id_factura) from Bill ").getResultList();
        return lastId.get(0)+1;
    }

    public void mergeSale(Sale sale){
        entityManager.persist(sale);
    }

    public Bill findBill(int id){
        return entityManager.find(Bill.class,id);
    }
}
