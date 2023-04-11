package com.techelevator.dao;

import com.techelevator.model.Equipment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component

public class JdbcEquipmentDao implements EquipmentDao {
    private final JdbcTemplate jdbcTemplate;


    public JdbcEquipmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /****************************************************************************
     *                              JdbcEquipmentDao                            *
     *                  access Equipment Table in database                      *
     ****************************************************************************/
    @Override
    public Equipment findEquipmentByEquipmentId(int equipmentId)
    {
        String sql = "SELECT  e.equipment_name, e.equipment_usage_date_time \n" +
                "FROM Equipment e\n" +
                "WHERE equipment_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, equipmentId);
        Equipment equipment = null ;

        if(result.next()) {
            equipment = mapRowToEquipment(result);
        }
        return equipment;
    }
    @Override
    public Equipment getEquipmentByBarcode (String barcode)
    {
        String sql = "SELECT equipment_name, equipment_usage_date_time  " +
                "FROM equipment " +
                "WHERE barcode = ?; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, barcode);
        Equipment equipment = null;
        if (result.next()) {
            equipment = mapRowToEquipment(result);
        }
        return equipment;
    }

    private Equipment mapRowToEquipment(SqlRowSet rowSet) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(rowSet.getInt("equipment_id"));
        equipment.setEquipmentName(rowSet.getString("equipment_name"));
        equipment.setEquipmentTutorial(rowSet.getString("equipment_tutorial"));
        return equipment;
    }
}
