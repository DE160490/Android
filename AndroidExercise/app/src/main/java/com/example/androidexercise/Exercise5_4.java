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

public class Exercise5_4 extends AppCompatActivity {
    public EditText num3;
    public EditText num4;
    public Button btnAdd1;
    public Button btnSub1;
    public Button btnMul1;
    public Button btnDiv1;
    public EditText result1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercise5_4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        num3 = findViewById(R.id.txtNum3);
        num4 = findViewById(R.id.txtNum4);
        btnAdd1 = findViewById(R.id.btnAdd1);
        btnSub1 = findViewById(R.id.btnSub1);
        btnMul1 = findViewById(R.id.btnMul1);
        btnDiv1 = findViewById(R.id.btnDiv1);
        result1 = findViewById(R.id.txtResult1);

        btnAdd1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                result1.setText(String.valueOf(Double.parseDouble(num3.getText().toString()) + Double.parseDouble(num4.getText().toString())));
            }
        });
        btnSub1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                result1.setText(String.valueOf(Double.parseDouble(num3.getText().toString()) - Double.parseDouble(num4.getText().toString())));
            }
        });
        btnMul1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                result1.setText(String.valueOf(Double.parseDouble(num3.getText().toString()) * Double.parseDouble(num4.getText().toString())));
            }
        });
        btnDiv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                result1.setText(String.valueOf(Double.parseDouble(num3.getText().toString()) / Double.parseDouble(num4.getText().toString())));
            }
        });
    }
}
