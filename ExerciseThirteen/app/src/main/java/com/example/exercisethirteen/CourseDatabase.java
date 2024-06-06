package com.example.exercisethirteen;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Course.class}, version = 1)
public abstract class CourseDatabase extends RoomDatabase {
    private static CourseDatabase instance;
    public abstract CourseDao courseDao();
    public static synchronized CourseDatabase getInstance(Context
                                                                  context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                        CourseDatabase.class, "course_database")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return instance;
    }
}

