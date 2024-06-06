package com.example.exercisethirteen;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "courses") // Set Table name
public class Course {
    // Table fields: id (Primary key - NonNull), name, description
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String description;
    public Course(@NonNull String id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    // Course ID
    @NonNull
    public String getId() {
        return id;
    }
    public void setId(@NonNull String id) {
        this.id = id;
    }
    // Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // Description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}