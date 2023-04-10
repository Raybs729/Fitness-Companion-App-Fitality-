package com.techelevator.model;

public class GymClassRegistrationRecord {
    private int userId;
    private int classId;

    public GymClassRegistrationRecord() {
    }

    public GymClassRegistrationRecord(int userId, int classId) {
        this.userId = userId;
        this.classId = classId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
