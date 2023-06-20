package com.codingideas.cashkeeper.controllers;

import com.codingideas.cashkeeper.interfaces.InventoryInterface;
import com.codingideas.cashkeeper.models.Inventory;
import com.codingideas.cashkeeper.models.InventoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/update")
    public boolean updateInventory(@RequestHeader(value = "Authorization") boolean auth,@RequestBody List<Inventory> inventoryList){
        return inventoryInterface.updateInventory(auth, inventoryList);
    }

    @RequestMapping(value = "/total")
    public ResponseEntity<InventoryRequest> totalInventory(@RequestHeader(value = "Authorization")boolean auth){
        return inventoryInterface.totalInventory(auth);
    }
}
