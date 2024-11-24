package com.example.pasmina;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private EditText etUsername, etEmail, etPassword;
    private Button btnLogin, registerButton;
    private ImageView backButton, cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.username);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginButton);
        backButton = findViewById(R.id.backButton);
        cartButton = findViewById(R.id.cartButton);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(Register.this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

            if (sharedPreferences.contains(username)) {
                Toast.makeText(Register.this, "Username sudah digunakan", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username + "_username", username);
                editor.putString(email + "_email", email);
                editor.putString(password + "_password", password);
                editor.apply();

                Toast.makeText(Register.this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        backButton.setOnClickListener(v -> {
            Toast.makeText(Register.this, "Kembali ke halaman sebelumnya", Toast.LENGTH_SHORT).show();
            finish();
        });

        cartButton.setOnClickListener(v -> {
            Toast.makeText(Register.this, "Membuka halaman keranjang", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Register.this, Keranjang.class);
            startActivity(intent);
        });
    }
}
