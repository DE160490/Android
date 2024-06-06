package com.example.exercisethirteen;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
public class UpdateCourseActivity extends AppCompatActivity {
    private EditText inputId, inputName, inputDescription;
    private Button buttonUpdate, buttonDelete;
    private CourseDatabase db;
    private Course course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_course);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v,
                                                                            insets) -> {
            Insets systemBars =
                    insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });
        inputId = findViewById(R.id.input_id);
        inputName = findViewById(R.id.input_name);
        inputDescription = findViewById(R.id.input_description);
        buttonUpdate = findViewById(R.id.button_update);
        buttonDelete = findViewById(R.id.button_delete);
        // Get database
        db = CourseDatabase.getInstance(this);
        // Get id to update/delete
        Intent intent = getIntent();
        final String courseId = intent.getStringExtra("course_id");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Get course by ID
                course = db.courseDao().getCourseById(courseId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (course != null) {
                            inputId.setText(course.getId());
                            inputName.setText(course.getName());

                            inputDescription.setText(course.getDescription());
                        }
                    }
                });
            }
        }).start();
        // Click Update button
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input data to update
                course.setName(inputName.getText().toString());
                course.setDescription(inputDescription.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.courseDao().update(course);// Update course
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        });
                    }
                }).start();
            }
        });
        // Click Delete button
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.courseDao().delete(course);// Delete course
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        });
                    }
                }).start();
            }
        });
    }
}