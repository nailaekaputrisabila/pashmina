package com.example.pasmina;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LupaPassword extends AppCompatActivity {

    private EditText emailInput;
    private Button sendResetLinkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        emailInput = findViewById(R.id.emailForgotInput);
        sendResetLinkButton = findViewById(R.id.sendResetLinkButton);

        sendResetLinkButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(LupaPassword.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            } else {
                sendResetEmail(email);
            }
        });
    }

    private void sendResetEmail(String email) {
        // Simulasikan pengiriman email reset
        // Integrasikan dengan layanan autentikasi seperti Firebase Authentication jika diperlukan
        Toast.makeText(this, "Reset link sent to " + email, Toast.LENGTH_SHORT).show();

        // Kembali ke halaman login
        Intent intent = new Intent(LupaPassword.this, Login.class);
        startActivity(intent);
        finish();
    }
}
