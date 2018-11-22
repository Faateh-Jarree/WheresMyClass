package com.droids.ffs.smd_project.SQLite;

import java.io.Serializable;

public class Class implements Serializable {


    private String _id;
    private String courseName;
    private String courseSection;
    private String classDay;
    private String classStartTime;
    private String classEndTime;
    private String classReminderTime;
    private String classRoom;
    private int thumbnail;

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Class(String courseName, String courseSection) {
        this.courseName = courseName;
        this.courseSection = courseSection;
    }

    public Class() {
        this._id = "";
        this.courseName = "";
        this.courseSection = "";
        this.classDay = "";
        this.classStartTime = "";
        this.classEndTime = "";
        this.classReminderTime = "";
        this.classRoom="";

    }

    public Class(String courseName, String courseSection, String classStartTime, String classEndTime, String classRoom) {
        this.courseName = courseName;
        this.courseSection = courseSection;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.classRoom = classRoom;
    }

    public Class(String courseName, String courseSection, String classDay, String classStartTime, String classEndTime, String classReminderTime, String classRoom) {
        this.courseName = courseName;
        this.courseSection = courseSection;
        this.classDay = classDay;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.classReminderTime = classReminderTime;
        this.classRoom = classRoom;
    }

    public Class(String courseName, String courseSection, String classDay, String classStartTime, String classEndTime, String classReminderTime, String classRoom, int thumbnail) {
        this.courseName = courseName;
        this.courseSection = courseSection;
        this.classDay = classDay;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.classReminderTime = classReminderTime;
        this.classRoom = classRoom;
        this.thumbnail = thumbnail;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(String courseSection) {
        this.courseSection = courseSection;
    }

    public String getClassDay() {
        return classDay;
    }

    public void setClassDay(String classDay) {
        this.classDay = classDay;
    }

    public String getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(String classStartTime) {
        this.classStartTime = classStartTime;
    }

    public String getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(String classEndTime) {
        this.classEndTime = classEndTime;
    }

    public String getClassReminderTime() {
        return classReminderTime;
    }

    public void setClassReminderTime(String classReminderTime) {
        this.classReminderTime = classReminderTime;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}
