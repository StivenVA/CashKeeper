package com.codingideas.cashkeeper.utils.mapper;

import com.codingideas.cashkeeper.dto.InventoryDTO;
import com.codingideas.cashkeeper.dto.TotalInventoryDTO;
import com.codingideas.cashkeeper.models.Inventory;
import com.codingideas.cashkeeper.models.Order;

public class MapperInventory {

    public InventoryDTO inventoryToInventoryDTO(Inventory inventory){

        InventoryDTO inventoryDTO = new InventoryDTO();

        inventoryDTO.setDescripcion(inventory.getId_producto().getDescripcion());
        inventoryDTO.setCongelador(inventory.getCongelador());
        inventoryDTO.setCantidad(inventory.getCantidad());
        inventoryDTO.setFecha(inventory.getFecha());
        inventoryDTO.setId_producto(inventory.getId_producto().getId());

        return inventoryDTO;
    }

    public TotalInventoryDTO inventoryAndOrdersToTotalInventoryDTO(Inventory inventory, Order order){
        TotalInventoryDTO totalInventoryDTO = new TotalInventoryDTO();

        return totalInventoryDTO;
    }
}
