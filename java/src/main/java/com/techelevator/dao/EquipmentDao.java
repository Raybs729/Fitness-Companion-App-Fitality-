package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.EquipmentUsageLog;

import java.util.List;

public interface EquipmentDao {
    public List<Equipment> listAll();
    public Equipment findEquipmentByEquipmentId(int equipmentId);
    public Equipment getEquipmentByBarcode (String barcode);
    public EquipmentUsageLog createEquipmentUsageLog (EquipmentUsageLog equipmentUsageLog);
}
