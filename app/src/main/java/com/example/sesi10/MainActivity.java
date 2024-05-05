package com.example.sesi10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView linkRegister;
    EditText usernameField, passwordField;
    Button login;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = findViewById(R.id.et_username);
        passwordField = findViewById(R.id.et_password);

        linkRegister = findViewById(R.id.tv_linkRegister);
        login = findViewById(R.id.btn_login);

        linkRegister.setOnClickListener(v -> {
            Intent loginIntent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(loginIntent);
        });

        sharedPreferences = getSharedPreferences("User_account", MODE_PRIVATE);

        login.setOnClickListener(v -> {
            if(!usernameField.getText().toString().equals(sharedPreferences.getString("Username_user", ""))) {
                Toast.makeText(this,"Username doesn't exist, please register!", Toast.LENGTH_SHORT).show();
            } else if (!passwordField.getText().toString().equals(sharedPreferences.getString("Password_user", ""))) {
                Toast.makeText(this,"Password incorrect!", Toast.LENGTH_SHORT).show();
            } else {
                Intent goHome = new Intent(MainActivity.this, HomeActivity.class);

                // Passing nilai
                goHome.putExtra("Username_user", sharedPreferences.getString("Username_user", ""));
                goHome.putExtra("Password_user", sharedPreferences.getString("Password_user", ""));
                goHome.putExtra("Email_user", sharedPreferences.getString("Email_user", ""));

                startActivity(goHome);
            }
        });
    }
}