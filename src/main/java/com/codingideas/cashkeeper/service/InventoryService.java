package com.codingideas.cashkeeper.service;

import com.codingideas.cashkeeper.dto.InventoryDTO;
import com.codingideas.cashkeeper.dto.TotalInventoryDTO;
import com.codingideas.cashkeeper.interfaces.InventoryInterface;
import com.codingideas.cashkeeper.models.*;
import com.codingideas.cashkeeper.repository.InventoryRespository;
import com.codingideas.cashkeeper.repository.OrderRepository;
import com.codingideas.cashkeeper.repository.ProductRespository;
import com.codingideas.cashkeeper.utils.mapper.MapperInventory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public boolean updateInventory(boolean auth, InventoryDTO inventoryDTO) {
        if (!auth) return false;

        Inventory inventory = new Inventory();
        Product product = productRespository.findProduct(inventoryDTO.getId_producto());

        inventory.setId_producto(product);
        inventory.setCongelador(inventoryDTO.getCongelador());
        inventory.setFecha(inventoryDTO.getFecha());
        inventory.setCantidad(inventoryDTO.getCantidad());
        System.out.println(inventory);

        inventoryRespository.mergeInventory(inventory);

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
    public ResponseEntity<InventoryRequest> totalInventory(boolean auth) {

        if(!auth) return ResponseEntity.badRequest().body(new InventoryRequest(null,"Error en la autenticacion"));

        List<Product> products = productRespository.getProducts();
        List<TotalInventoryDTO> totalInventoryDTOList = new ArrayList<>();
        List<OrderDetail> orders = orderRepository.getProductsForLastOrder();
        List<Inventory> inventory= inventoryRespository.getLastInventory();
        List<Order> LastOrder = orderRepository.getLastOrders();

        if (!inventory.get(0).getFecha().equals(LastOrder.get(0).getFecha())) return ResponseEntity.badRequest().body(new InventoryRequest(null,"Las fecha del ultimo pedido y del inventario no coinciden"));

        for (int i = 0; i < products.size(); i++) {

            TotalInventoryDTO totalInventoryDTO = new TotalInventoryDTO(products.get(i).getId(),products.get(i).getDescripcion(),0,LastOrder.get(0).getFecha());
            totalInventoryDTOList.add(totalInventoryDTO);

            String id_productoInventoryDTO = totalInventoryDTOList.get(i).getId_producto();
            for (OrderDetail order : orders) {

                String id_productoOrder = order.getId_producto().getId();

                if (id_productoInventoryDTO.equals(id_productoOrder)) {
                    totalInventoryDTOList.get(i).setCantidad(totalInventoryDTOList.get(i).getCantidad() + order.getCantidad());
                }

            }
            for (Inventory value : inventory) {
                String id_productoInventory = value.getId_producto().getId();

                if (id_productoInventoryDTO.equals(id_productoInventory)) {
                    totalInventoryDTOList.get(i).setCantidad(totalInventoryDTOList.get(i).getCantidad() + value.getCantidad());
                }
            }
        }

        return ResponseEntity.ok().body(new InventoryRequest(totalInventoryDTOList,null));
    }


}
