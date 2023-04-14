package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.EquipmentUsageLog;

import java.util.List;

public interface EquipmentDao {
    public List<Equipment> listAll();
    public Equipment findEquipmentByEquipmentId(int equipmentId);
    public Equipment getEquipmentByBarcode (String barcode);
    public EquipmentUsageLog createEquipmentUsageLog (EquipmentUsageLog equipmentUsageLog);
    public boolean createEquipment (String equipmentName);
    public Equipment getEquipmentByEquipmentName (String equipmentName);
    public String getEquipmentNameByExerciseName (String exerciseName);
    public List<EquipmentUsageLog> getMachineMetrics ();
}
