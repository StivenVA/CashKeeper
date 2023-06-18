package com.codingideas.cashkeeper.models;

import com.codingideas.cashkeeper.dto.TotalInventoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {

    private List<TotalInventoryDTO> totalInventory;
    private String error;
}
