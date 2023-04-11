package com.techelevator.dao;

import com.techelevator.model.Equipment;

public interface EquipmentDao {

    public Equipment findEquipmentByEquipmentId(int equipmentId);
    public Equipment getEquipmentByBarcode (String barcode);
}
