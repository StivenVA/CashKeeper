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

    public List<Sale> getProducts(){
        return entityManager.createQuery("from Sale order by id_factura.id_factura").getResultList();
    }

    public List<Long> getNumberOfBills(){
        return entityManager
                .createQuery("select count(id_factura) from Sale group by id_factura").getResultList();
    }

    public List<Bill> getBills(){
        return entityManager.createQuery("from Bill").getResultList();
    }

    public void updateSale(Sale saleEdited){

        List<Sale> sale=entityManager.createQuery("from Sale where id_factura=:id_factura and id_producto=:id_producto")
                .setParameter("id_factura",saleEdited.getId_factura())
                .setParameter("is_producto",saleEdited.getId_producto()).getResultList();

        sale.get(0).setCantidad(saleEdited.getCantidad());

        entityManager.merge(sale.get(0));

    }

    public void updateBill(Sale sale){
        Bill bill = entityManager.find(Bill.class,sale.getId_factura().getId_factura());

        bill.setTotal(sale.getId_factura().getTotal());

        entityManager.merge(bill);
    }

    public void addSale(@NotNull Sale sale,@NotNull Bill bill){

        entityManager.merge(bill);
        sale.setId_factura(bill);
        entityManager.merge(sale);
    }
}
