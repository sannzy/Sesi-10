package com.example.sesi10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView username, email, password;
    AlertDialog alertDialog;
    Button pdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = findViewById(R.id.tv_username);
        email = findViewById(R.id.tv_email);
        password = findViewById(R.id.tv_password);
        pdialog = findViewById(R.id.btn_pdialog);

        Intent intent = getIntent();
        String uname = intent.getStringExtra("Username_user");
        String mail = intent.getStringExtra("Email_user");
        String psw = intent.getStringExtra("Password_user");

        username.setText(uname);
        email.setText(mail);
        password.setText(psw);

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Logout??").setMessage("Are you sure want to log out?").setPositiveButton("Log Out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog = builder.create();

        ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("App is on the run, please wait...");
        progressDialog.setCancelable(true);

        pdialog.setOnClickListener(v -> {
            progressDialog.show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menu = item.getItemId();
        switch (menu) {
            case R.id.logout:
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

// alert dialog -> membuat pilihan ke user mau memilih apa. Are you sure want to exit? yes/no
// progress dialog -> membuat user tau kalau masi ada program yang masi berjalan