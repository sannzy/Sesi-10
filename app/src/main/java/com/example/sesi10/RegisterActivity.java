package com.example.sesi10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    TextView linkLogin;
    EditText emailField, usernameField, passwordField, confirmField;
    Button register;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailField = findViewById(R.id.et_email);
        usernameField = findViewById(R.id.et_username);
        passwordField = findViewById(R.id.et_password);
        confirmField = findViewById(R.id.et_confirm);

        linkLogin = findViewById(R.id.tv_linkLogin);
        register = findViewById(R.id.btn_register);

        linkLogin.setOnClickListener(v -> {
            Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(loginIntent);
        });

        sharedPreferences = getSharedPreferences("User_account", MODE_PRIVATE);

        register.setOnClickListener(v ->{ // v adalah view
            if(!emailField.getText().toString().contains("@")
                    || !emailField.getText().toString().contains(".com")){
                Toast.makeText(this,"Email must be valid and ends with .com", Toast.LENGTH_SHORT).show();
            } else if (usernameField.getText().toString().length() < 3) {
                Toast.makeText(this,"Username must be more than 3 characters.", Toast.LENGTH_SHORT).show();
            } else if (passwordField.getText().toString().length() < 5) {
                Toast.makeText(this,"Password must be more than 5 characters.", Toast.LENGTH_SHORT).show();
            } else if (!confirmField.getText().toString().equals(passwordField.getText().toString())) {
                Toast.makeText(this,"Password must be matched.", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Email_user", emailField.getText().toString());
                editor.putString("Username_user", usernameField.getText().toString());
                editor.putString("Password_user", passwordField.getText().toString());
                editor.apply();

                // Untuk memberikan notifikasi buat user kalau register udah selesai:
                Toast.makeText(this,"Registration completed. Please login!", Toast.LENGTH_LONG).show();

                // Setelah klik button register, pindah ke login page
                Intent toLogin = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(toLogin);

                finish(); // Untuk gak balik lagi ke activity sebelumnya
            }
        });
    }
}