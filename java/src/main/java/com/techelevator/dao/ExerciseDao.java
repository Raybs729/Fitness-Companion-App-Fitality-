package com.techelevator.dao;

import com.techelevator.model.EquipmentExercise;
import com.techelevator.model.Exercise;
import com.techelevator.model.ExerciseInfo;

import java.util.List;

public interface ExerciseDao {
    public List<Exercise> getAllExercise ();
    public List<ExerciseInfo> getExerciseInfoByUserId (int userId);
    public List<ExerciseInfo> getExerciseInfoByEquipmentId (int equipmentId);
//    public List<ExerciseInfo> getExerciseInfoByDate (String  equipmentUsageDateTime);
    public void createExercise (String exerciseName);
    public void createExerciseInfo (ExerciseInfo exerciseInfo);
    public void createEquipmentUsageLog (ExerciseInfo exerciseInfo);
    public void createEquipmentExercise (EquipmentExercise equipmentExercise);
}
