package com.codingideas.cashkeeper.service;

import com.codingideas.cashkeeper.interfaces.InventoryInterface;
import com.codingideas.cashkeeper.models.Inventory;
import com.codingideas.cashkeeper.models.Product;
import com.codingideas.cashkeeper.repository.InventoryRespository;
import com.codingideas.cashkeeper.repository.OrderRepository;
import com.codingideas.cashkeeper.repository.ProductRespository;
import com.codingideas.cashkeeper.utils.mapper.MapperInventory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
@RequiredArgsConstructor
public class InventoryService implements InventoryInterface {

    private final InventoryRespository inventoryRespository;
    private final ProductRespository productRespository;
    private final OrderRepository orderRepository;

    @Override
    public List getLastInventory(boolean auth) {
        if (!auth) return null;

        MapperInventory mapperInventory = new MapperInventory();

        return inventoryRespository.getLastInventory()
                .stream().map(mapperInventory::inventoryToInventoryDTO).collect(Collectors.toList());
    }

    @Override
    public boolean updateInventory(boolean auth, List<Inventory> inventoryList) {
        if (!auth) return false;

        for (Inventory inventory:
             inventoryList) {
            Product product = productRespository.findProduct(inventory.getId_producto().getId());
            inventory.setId_producto(product);

            inventoryRespository.mergeInventory(inventory);
        }

        return false;
    }

    @Override
    public boolean editInventory(boolean auth, Inventory inventoryEdited) {
        if (!auth) return false;
        Product product = productRespository.findProduct(inventoryEdited.getId_producto().getId());
        Inventory inventory = inventoryRespository.findInventory(inventoryEdited.getCongelador(),product);

        inventory.setCantidad(inventory.getCantidad());
        inventoryRespository.mergeInventory(inventory);

        return true;
    }

    @Override
    public List totalInventory() {
        return null;
    }


}
