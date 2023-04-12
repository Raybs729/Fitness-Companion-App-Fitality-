package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.EquipmentUsageLog;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component

public class JdbcEquipmentDao implements EquipmentDao {
    private final JdbcTemplate jdbcTemplate;


    public JdbcEquipmentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    /****************************************************************************
     *                              JdbcEquipmentDao                            *
     *                  access Equipment Table in database                      *
     ****************************************************************************/
    @Override
    public Equipment findEquipmentByEquipmentId(int equipmentId)
    {
        String sql = "SELECT * \n" +
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
    public Equipment getEquipmentByBarcode (String barcode) {
        String sql = "SELECT *  " +
                "FROM equipment " +
                "WHERE barcode = ?; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, barcode);
        Equipment equipment = null;
        if (result.next()) {
            equipment = mapRowToEquipment(result);
        }
        return equipment;
    }

    @Override
    public EquipmentUsageLog createEquipmentUsageLog(EquipmentUsageLog equipmentUsageLog) {
        String sql = "INSERT INTO public.equipmentusagelog(\n" +
                "\tuser_id, equipment_id, equipment_usage_date_time)\n" +
                "\tVALUES (?, ?, ?);";
        Integer newLogId = jdbcTemplate.queryForObject(sql, Integer.class, equipmentUsageLog.getUserId(), equipmentUsageLog.getEquipmentId(), equipmentUsageLog.getEquipmentUsageDateTime());

        if (newLogId == null) {
            return null;
        }
        return equipmentUsageLog;
    }


    private Equipment mapRowToEquipment(SqlRowSet rowSet) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(rowSet.getInt("equipment_id"));
        equipment.setEquipmentName(rowSet.getString("equipment_name"));
        equipment.setEquipmentTutorial(rowSet.getString("equipment_tutorial"));
        return equipment;
    }


}
