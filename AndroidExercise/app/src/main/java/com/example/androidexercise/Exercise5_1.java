package com.example.androidexercise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Exercise5_1 extends AppCompatActivity {
    public Button myButton;
    public EditText myUser;
    public EditText myPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercise5_1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myButton = findViewById(R.id.btnOk);
        myUser = findViewById(R.id.editUser);
        myPass = findViewById(R.id.editPassword);
    }

    public void onLogin(View view){
        if(myUser.getText().toString().equals("admin") && myPass.getText().toString().equals("123")){
            Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
