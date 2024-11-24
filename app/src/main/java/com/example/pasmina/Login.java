package com.example.pasmina;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;
    private TextView registerButton;
    private TextView forgotPassword;
    private ImageView cartButton;
    private ImageView backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        cartButton = findViewById(R.id.cartButton);
        backButton = findViewById(R.id.backButton);
        forgotPassword = findViewById(R.id.forgotPassword);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                String savedEmail = sharedPreferences.getString(email + "_email", null);
                String savedPassword = sharedPreferences.getString(password + "_password", null);

                if (email.equals(savedEmail) && password.equals(savedPassword)) {
                    Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Login.this, Promosi.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(v -> {
            Toast.makeText(Login.this, "Kembali ke halaman sebelumnya", Toast.LENGTH_SHORT).show();
            finish();
        });

        cartButton.setOnClickListener(v -> {
            Toast.makeText(Login.this, "Membuka halaman keranjang", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, Keranjang.class);
            startActivity(intent);
        });
        forgotPassword.setOnClickListener(v -> {
            Toast.makeText(Login.this, "Reset Password", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, LupaPassword.class);
            startActivity(intent);
        });
    }
}


