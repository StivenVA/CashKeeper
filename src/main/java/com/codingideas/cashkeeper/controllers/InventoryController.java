package com.codingideas.cashkeeper.controllers;

import com.codingideas.cashkeeper.dto.InventoryDTO;
import com.codingideas.cashkeeper.interfaces.InventoryInterface;
import com.codingideas.cashkeeper.models.Inventory;
import com.codingideas.cashkeeper.models.InventoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/inventory")
@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryInterface inventoryInterface;

    @RequestMapping(value = "/get")
    public List getInventoryFridges(@RequestHeader(value = "Authorization")boolean auth){
        return inventoryInterface.getLastInventory(auth);
    }

    @RequestMapping(value = "/edit")
    public boolean editInventory(@RequestHeader(value = "Authorization") boolean auth, @RequestBody Inventory inventory){
        return inventoryInterface.editInventory(auth, inventory);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public boolean updateInventory(@RequestHeader(value = "Authorization") boolean auth,@RequestBody InventoryDTO inventoryDTO){
        return inventoryInterface.updateInventory(auth, inventoryDTO);
    }

    @RequestMapping(value = "/total")
    public ResponseEntity<InventoryRequest> totalInventory(@RequestHeader(value = "Authorization")boolean auth){
        return inventoryInterface.totalInventory(auth);
    }
}
