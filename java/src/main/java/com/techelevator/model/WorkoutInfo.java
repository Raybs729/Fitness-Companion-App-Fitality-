package com.techelevator.model;

import org.springframework.security.access.prepost.PreAuthorize;

import java.sql.Time;
import java.time.YearMonth;

/*****************
 * this class is responsible for displaying the number of times a user
 * visited the gym during one month and how long their workouts were
 * on average
 */

public class WorkoutInfo {

    private String month;
    private int userVisits;
    private Time avgDuration;

    public WorkoutInfo(String month, int userVisits, Time avgDuration) {
        this.month = month;
        this.userVisits = userVisits;
        this.avgDuration = avgDuration;
    }

    public WorkoutInfo() {}

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getUserVisits() {
        return userVisits;
    }

    public void setUserVisits(int userVisits) {
        this.userVisits = userVisits;
    }

    public Time getAvgDuration() {
        return avgDuration;
    }

    public void setAvgDuration(Time avgDuration) {
        this.avgDuration = avgDuration;
    }
}
