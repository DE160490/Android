package com.example.exercisetwelve;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private DBHandler dbHandler;
    private CourseAdapter courseAdapter;
    private List<Course> courseList;
    private RecyclerView recyclerView;
    private Button buttonAdd;
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

    // Get data
        dbHandler = new DBHandler(this);
        courseList = dbHandler.getAllCourses();
        recyclerView = findViewById(R.id.recycler_view);
        buttonAdd = findViewById(R.id.button_add);

    // Set the LayoutManager to recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    // Set data to Adapter
        courseAdapter = new CourseAdapter(courseList);

    // Set the Adapter to RecyclerView
        recyclerView.setAdapter(courseAdapter);

    // Add a Listener to RecyclerView Adapter
        courseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
         // Click an item
         @Override
         public void onItemClick(int position) {
             Intent intent = new Intent(MainActivity.this, UpdateCourseActivity.class);
             intent.putExtra("course_id", courseList.get(position).getId());
             startActivity(intent);
         }

         // Click Edit button
         @Override
         public void onEditClick(int position) {
             Intent intent = new Intent(MainActivity.this, UpdateCourseActivity.class);
             intent.putExtra("course_id", courseList.get(position).getId());
             startActivity(intent);
         }

         // Click Delete button
         @Override
         public void onDeleteClick(int position) {
             dbHandler.deleteCourse(courseList.get(position));
             courseList.remove(position);
             courseAdapter.notifyItemRemoved(position);
         }});

    // Add a Listener to Add button
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
        courseList.clear();
        courseList.addAll(dbHandler.getAllCourses());
        courseAdapter.notifyDataSetChanged();
    }
}