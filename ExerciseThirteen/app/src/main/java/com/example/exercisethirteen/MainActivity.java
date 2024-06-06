package com.example.exercisethirteen;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button buttonAdd;
    private CourseAdapter courseAdapter;
    private List<Course> courseList = new ArrayList<>();
    private CourseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recycler_view);
        buttonAdd = findViewById(R.id.button_add);
        // Database
        db = CourseDatabase.getInstance(this);
        // Set the LayoutManager to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Set the Adapter to RecyclerView
        courseAdapter = new CourseAdapter(courseList);
        recyclerView.setAdapter(courseAdapter);
        // Add a listener to RecylerView Adapter
        courseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(int position) { // Click an Item
                 Intent intent = new Intent(MainActivity.this, UpdateCourseActivity.class);
                 intent.putExtra("course_id", courseList.get(position).getId());
                 startActivity(intent);
             }

             @Override
             public void onEditClick(int position) {// Click Update button
                 Intent intent = new Intent(MainActivity.this, UpdateCourseActivity.class);
                 intent.putExtra("course_id", courseList.get(position).getId());
                 startActivity(intent);
             }

             @Override
             public void onDeleteClick(int position) { // Click Delete button
                 final Course course = courseList.get(position);
                 new Thread( new Runnable() {
                     @Override
                     public void run() {
                         db.courseDao().delete(course);
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 courseList.remove(course);
                                 courseAdapter.notifyDataSetChanged();
                             }
                         });
                     }
                 }).start();
             }
         });
        // Add a listener to Add button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
                startActivity(intent);
            }
        });
    }
    // Refresh data
    @Override
    protected void onResume() {
        super.onResume();
        loadCourses();
    }
    private void loadCourses() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                courseList.clear();
                courseList.addAll(db.courseDao().getAllCourses());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        courseAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
