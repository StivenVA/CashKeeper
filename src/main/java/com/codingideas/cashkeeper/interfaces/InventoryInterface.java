package com.codingideas.cashkeeper.interfaces;

import com.codingideas.cashkeeper.models.Inventory;

import java.util.List;

public interface InventoryInterface {

    public List getLastInventory(boolean auth);

    public boolean updateInventory(boolean auth, List<Inventory> inventory);

    public boolean editInventory(boolean auth, Inventory inventory);

    List totalInventory();
}
