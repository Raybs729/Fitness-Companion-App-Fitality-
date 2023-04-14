package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.EquipmentUsageLog;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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
    public List<Equipment> listAll() {
        List<Equipment> equipmentList = new ArrayList<>();

        String sql = "SELECT equipment_id, equipment_name, equipment_tutorial, barcode " +
                "FROM equipment; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
             equipmentList.add(mapRowToEquipment(results));
        }
        return equipmentList;
    }

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

    @Override
    public boolean createEquipment(Equipment equipment) {
        String sql = "INSERT INTO equipment" + " (equipment_name, equipment_tutorial, barcode)"+
                " VALUES (?, ?, ?);";
        Integer newEquipmentId = jdbcTemplate.queryForObject(sql, Integer.class, equipment.getEquipmentName(), equipment.getEquipmentTutorial(), equipment.getBarcode());

        return newEquipmentId != null;
    }

    @Override
    public Equipment getEquipmentByEquipmentName(String equipmentName) {
        Equipment equipment = new Equipment();
        String sql = "SELECT * FROM equipment WHERE equipment_name = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, equipmentName);
        if (result.next()) {
            equipment = mapRowToEquipment(result);
        }

        return equipment;
    }

    @Override
    public String getEquipmentNameByExerciseName(String exerciseName) {
        String equipmentName = null;
        Equipment equipment = null;
        String sql = "SELECT * FROM equipment e " +
                "JOIN equipment_exercise ee ON ee.equipment_id = e.equipment_id " +
                "JOIN exercise ex ON ex.exercise_id = ee.exercise_id " +
                "WHERE exercise_name = ?;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, exerciseName);

        if (sqlRowSet.next()) {
            equipment = mapRowToEquipment(sqlRowSet);
            equipmentName = equipment.getEquipmentName();
        }

        return equipmentName;

    }

    public List<EquipmentUsageLog> getMachineMetrics (){
        List<EquipmentUsageLog> log = new ArrayList<>();
        String sql = "SELECT COUNT(eul.equipment_id) AS \"total_usage\", e.equipment_name\n" +
                "FROM equipmentusagelog eul \n" +
                "INNER JOIN equipment e on e.equipment_id = eul.equipment_id\n" +
                "WHERE eul.equipment_usage_date_time::text LIKE '2023-04-%'\n" +
                "GROUP BY eul.equipment_id, e.equipment_name\n" +
                "ORDER BY eul.equipment_id ASC;\n;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            log.add(mapRowToEquipmentUsageLog(results));
        }
        return log;
    }



    private Equipment mapRowToEquipment(SqlRowSet rowSet) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(rowSet.getInt("equipment_id"));
        equipment.setEquipmentName(rowSet.getString("equipment_name"));
        equipment.setEquipmentTutorial(rowSet.getString("equipment_tutorial"));
        return equipment;
    }

    private EquipmentUsageLog mapRowToEquipmentUsageLog (SqlRowSet rowSet){
        EquipmentUsageLog log = new EquipmentUsageLog();
        log.setTotalUsage(rowSet.getInt("total_usage"));
        log.setEquipmentName(rowSet.getString("equipment_name"));
        return log;
    }
}
