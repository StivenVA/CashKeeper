package com.codingideas.cashkeeper.interfaces;

import com.codingideas.cashkeeper.models.Inventory;
import com.codingideas.cashkeeper.models.InventoryRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryInterface {

    public List getLastInventory(boolean auth);

    public boolean updateInventory(boolean auth, List<Inventory> inventory);

    public boolean editInventory(boolean auth, Inventory inventory);

    ResponseEntity<InventoryRequest> totalInventory(boolean auth);
}
