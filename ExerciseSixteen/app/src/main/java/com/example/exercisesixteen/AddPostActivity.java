package com.example.exercisesixteen;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity {
    private EditText etTitle, etBody;
    private Button btnSave;
    private ApiService apiService;
    private Integer postId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        btnSave = findViewById(R.id.btnSave);
        apiService = ApiClient.getApiService();

        Intent intent = getIntent();
        if (intent.hasExtra("postId")) {
            postId = intent.getIntExtra("postId", 0);
            etTitle.setText(intent.getStringExtra("title"));
            etBody.setText(intent.getStringExtra("body"));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String body = etBody.getText().toString();
                if (title.isEmpty() || body.isEmpty()) {
                    Toast.makeText(AddPostActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (postId != null && postId.intValue() > 0) {
                    updatePost( new Post(postId, title, body));
                } else {
                    createPost(new Post(0, title, body));
                }
            }
        });
    }
    private void createPost(Post post) {
        apiService.createPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AddPostActivity.this, "Post created successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity and go back to the main activity
                }
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(AddPostActivity.this, "Failed to create post", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updatePost(Post post) {
        apiService.updatePost(postId, post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddPostActivity.this, "Post updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(AddPostActivity.this, "Failed to update post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
