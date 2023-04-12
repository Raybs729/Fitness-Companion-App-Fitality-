package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.EquipmentUsageLog;

public interface EquipmentDao {

    public Equipment findEquipmentByEquipmentId(int equipmentId);
    public Equipment getEquipmentByBarcode (String barcode);
    public EquipmentUsageLog createEquipmentUsageLog (EquipmentUsageLog equipmentUsageLog);
}
