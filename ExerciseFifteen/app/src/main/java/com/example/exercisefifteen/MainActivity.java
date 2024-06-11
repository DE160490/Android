package com.example.exercisefifteen;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText, phoneEditText;
    private TextView resultTextView;
    private Button addButton, queryButton;

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

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        resultTextView = findViewById(R.id.resultTextView);
        addButton = findViewById(R.id.addButton);
        queryButton = findViewById(R.id.queryButton);

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
        queryButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               queryContacts();
           }
       });
    }

    private void addContact() {
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, name);
        values.put(DBHelper.COLUMN_PHONE, phone);
        Uri newUri = getContentResolver().insert(ContactsProvider.CONTENT_URI, values);
        resultTextView.setText("Added Contact: " + newUri.toString());
    }

    private void queryContacts() {
        Cursor cursor = getContentResolver().query(ContactsProvider.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            resultTextView.setText("");
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PHONE));
                resultTextView.append("ID: " + id + ", Name: " + name + ", Phone: " + phone + "\n");
            }
            cursor.close();
        }
    }
}