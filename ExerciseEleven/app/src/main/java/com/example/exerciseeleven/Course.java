package com.example.exerciseeleven;

public class Course {
    private String id;
    private String name;
    private String description;

    public Course(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    // Getters and Setters

    // Course ID
    public String getId() {
        return id;
    }
    public void setId(String id) {
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