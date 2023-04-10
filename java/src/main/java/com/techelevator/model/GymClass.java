package com.techelevator.model;

public class GymClass {

    private int classId;
    private String class_name;
    private String dateTimeStart;
    private String dateTimeEnd;
    private int signedUp;

    public GymClass() {}

    public GymClass(int classId, String class_name, String dateTimeStart, String dateTimeEnd, int signedUp) {
        this.classId = classId;
        this.class_name = class_name;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.signedUp = signedUp;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(String dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public String getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(String dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public int getSignedUp() {
        return signedUp;
    }

    public void setSignedUp(int signedUp) {
        this.signedUp = signedUp;
    }
}

