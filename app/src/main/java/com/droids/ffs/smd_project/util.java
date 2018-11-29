package com.droids.ffs.smd_project;

import com.droids.ffs.smd_project.SQLite.Class;

import java.util.ArrayList;
import java.util.List;

public class util {


    public static List<Class> removeSameCoursesDifferentSections(List<Class> classes, Class _class){

        List<Integer> removeIndexes = new ArrayList<>();


        for (int i = 0; i < classes.size(); i++){
            if(classes.get(i).getCourseName().matches(_class.getCourseName()) || classes.get(i).getCourseShortname().matches(_class.getCourseShortname())){
                removeIndexes.add(i);
            }
        }

        for (int i = 0; i < removeIndexes.size(); i++){
            classes.remove(removeIndexes.get(i));
        }

        return classes;
    }


//    public static List<Class> sortSelectedClassesOnTime(List<Class> classes){
//
//    }


}
