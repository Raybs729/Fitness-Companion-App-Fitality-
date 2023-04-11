package com.techelevator.dao;

import com.techelevator.model.ExerciseInfo;

import java.util.List;

public interface ExerciseDao {

    public List<ExerciseInfo> getExerciseInfoByUserId (int userId);
    public List<ExerciseInfo> getExerciseInfoByEquipmentId (int equipmentId);
    public List<ExerciseInfo> getExerciseInfoByDate (String  equipmentUsageDateTime);
}
