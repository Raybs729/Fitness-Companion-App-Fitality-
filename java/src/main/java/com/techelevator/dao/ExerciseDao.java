package com.techelevator.dao;

import com.techelevator.model.EquipmentUsageLog;
import com.techelevator.model.Exercise;
import com.techelevator.model.ExerciseInfo;

import java.util.List;

public interface ExerciseDao {
    public List<Exercise> getAllExercise ();
    public List<ExerciseInfo> getExerciseInfoByUserId (int userId);
    public List<ExerciseInfo> getExerciseInfoByEquipmentId (int equipmentId);
//    public List<ExerciseInfo> getExerciseInfoByDate (String  equipmentUsageDateTime);
    public boolean createExercise (Exercise exercise);
    public ExerciseInfo createExerciseInfo (ExerciseInfo exerciseInfo);
    public EquipmentUsageLog createEquipmentUsageLog (ExerciseInfo exerciseInfo);
}
