package com.example.exercisethirteen;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao
public interface CourseDao {
    // Insert data
    @Insert
    void insert(Course course);
    // Update data
    @Update
    void update(Course course);
    // Delete data
    @Delete
    void delete(Course course);
    // Get all data
    @Query("SELECT * FROM courses")
    List<Course> getAllCourses();
    // Get data by id
    @Query("SELECT * FROM courses WHERE id = :id")
    Course getCourseById(String id);
}