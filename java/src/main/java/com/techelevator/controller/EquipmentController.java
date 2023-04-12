package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.dao.EquipmentDao;
import com.techelevator.dao.JdbcAccountDao;
import com.techelevator.dao.JdbcEquipmentDao;
import com.techelevator.model.Account;
import com.techelevator.model.Equipment;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController
@CrossOrigin
@RequestMapping("/equipments")
public class EquipmentController {
    private EquipmentDao dao;

    public EquipmentController () {
            this.dao = new JdbcEquipmentDao(dataSource());
    }

      @GetMapping("/{equipment_id}" )
      public Equipment getEquipmentById (@PathVariable("equipment_id") int equipmentId) {
        return dao.findEquipmentByEquipmentId(equipmentId);
      }

    private BasicDataSource dataSource(){
            BasicDataSource dataSource = new BasicDataSource();

            dataSource.setUrl("jdbc:postgresql://localhost:5432/final_capstone1");
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres1");

            return dataSource;
    }
}

