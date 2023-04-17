package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.EquipmentUsageLog;
import com.techelevator.model.MachineMetric;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
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

        String sql = "SELECT equipment_id, equipment_name, equipment_tutorial " +
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
    public void createEquipment(Equipment equipment) {
        String sql = "INSERT INTO equipment" + " (equipment_name, equipment_tutorial)"+
                " VALUES (?, ?);";
        jdbcTemplate.update(sql, equipment.getEquipmentName(), equipment.getEquipmentTutorial());

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

    public List<MachineMetric> getMachineMetrics (String date){
        List<MachineMetric> log = new ArrayList<>();
        String sql = "SELECT e.equipment_name, COUNT(eul.equipment_id) AS total_usage\n" +
                "FROM equipmentusagelog eul\n" +
                "INNER JOIN equipment e on e.equipment_id = eul.equipment_id\n" +
                "WHERE eul.equipment_usage_date_time::text LIKE CONCAT('%', ?, '%')\n" +
                "GROUP BY eul.equipment_id, e.equipment_name\n" +
                "ORDER BY total_usage DESC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date);
        while (results.next()) {
            log.add(mapRowToMachineMetric(results));
        }

        try {
            if (log.isEmpty()) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("No usages were reported during this period.");
        }

        return log;
    }

    public List <MachineMetric> getMachineMetricsByName (String date, String equipmentName) {
        List<MachineMetric> log = new ArrayList<>();
        String sql = "SELECT e.equipment_name, COUNT(eul.equipment_id) AS total_usage\n" +
                "FROM equipmentusagelog eul\n" +
                "INNER JOIN equipment e on e.equipment_id = eul.equipment_id\n" +
                "WHERE eul.equipment_usage_date_time::text LIKE CONCAT('%', ?, '%')\n" +
                "AND e.equipment_id = (SELECT equipment_id FROM equipment WHERE equipment_name = ?)\n" +
                "GROUP BY eul.equipment_id, e.equipment_name\n" +
                "ORDER BY total_usage DESC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date, equipmentName);
        while (results.next()) {
            log.add(mapRowToMachineMetric(results));
        }

        try {
            if (log.isEmpty()) {
                throw new Exception("No usages were reported during the specified time.");
            }
        } catch (Exception e) {
            e.getMessage();
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

    private MachineMetric mapRowToMachineMetric (SqlRowSet rowSet){
        MachineMetric machineMetric = new MachineMetric();
        machineMetric.setEquipmentName(rowSet.getString("equipment_name"));
        machineMetric.setTotalUsage(rowSet.getInt("total_usage"));
        return machineMetric;
    }
}
