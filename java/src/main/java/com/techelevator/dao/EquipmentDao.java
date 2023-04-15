package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.EquipmentUsageLog;

import java.util.List;

public interface EquipmentDao {
    public List<Equipment> listAll();
    public Equipment findEquipmentByEquipmentId(int equipmentId);
    public EquipmentUsageLog createEquipmentUsageLog (EquipmentUsageLog equipmentUsageLog);
    public void createEquipment (Equipment equipment);
    public Equipment getEquipmentByEquipmentName (String equipmentName);
    public String getEquipmentNameByExerciseName (String exerciseName);
    public List<EquipmentUsageLog> getMachineMetrics ();
}
