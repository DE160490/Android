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

import org.w3c.dom.Text;

public class Exercise5_3 extends AppCompatActivity {
    public EditText num1;
    public EditText num2;
    public Button btnAdd;
    public Button btnSub;
    public Button btnMul;
    public Button btnDiv;
    public EditText result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercise5_3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        num1 = findViewById(R.id.txtNum1);
        num2 = findViewById(R.id.txtNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        result = findViewById(R.id.txtResult);

    }

    public void onAdd(View view){
        result.setText(String.valueOf(Double.parseDouble(num1.getText().toString()) + Double.parseDouble(num2.getText().toString())));
    }
    public void onSub(View view){
        result.setText(String.valueOf(Double.parseDouble(num1.getText().toString()) - Double.parseDouble(num2.getText().toString())));
    }
    public void onMul(View view){
        result.setText(String.valueOf(Double.parseDouble(num1.getText().toString()) * Double.parseDouble(num2.getText().toString())));
    }
    public void onDiv(View view){
        result.setText(String.valueOf(Double.parseDouble(num1.getText().toString()) / Double.parseDouble(num2.getText().toString())));
    }

}
