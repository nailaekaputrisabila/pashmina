package com.example.pasmina;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        EditText searchEditText = findViewById(R.id.searchEditText);
        ImageView searchIcon = findViewById(R.id.searchIcon);
        TextView loginTextView = findViewById(R.id.loginTextView);
        TextView shopTextView = findViewById(R.id.shopTextView);

        TextView hijabTextView = findViewById(R.id.hijabTextView);
        TextView pashminaKaosTextView = findViewById(R.id.pashminaKaosTextView);
        TextView pashminaJerseyTextView = findViewById(R.id.pashminaJerseyTextView);

        // Tombol pencarian diklik
        searchIcon.setOnClickListener(v -> performSearch(searchEditText));

        // Menangani ketika tombol Enter ditekan pada keyboard
        searchEditText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                performSearch(searchEditText);
                return true;
            }
            return false;
        });

        // Arahkan ke halaman login
        loginTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, Login.class);
            startActivity(intent);
        });

        // Arahkan ke halaman shop
        shopTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, PashminaKaos.class);
            startActivity(intent);
        });

        // Arahkan ke kategori Pashmina Kaos
        pashminaKaosTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, PashminaKaos.class);
            startActivity(intent);
        });
    }

    // Fungsi untuk melakukan pencarian
    private void performSearch(EditText searchEditText) {
        String query = searchEditText.getText().toString().trim();
        if (!query.isEmpty()) {
            // Pencarian sesuai kata kunci
            if (query.equalsIgnoreCase("pashmina kaos")) {
                // Jika kata kunci adalah "pashmina kaos", buka halaman PashminaKaos
                Intent intent = new Intent(Menu.this, PashminaKaos.class);
                startActivity(intent);
                Toast.makeText(Menu.this, "Mencari Pashmina Kaos", Toast.LENGTH_SHORT).show();
            } else if (query.equalsIgnoreCase("pashmina jersey")) {
                // Jika kata kunci adalah "pashmina jersey", buka halaman PashminaJersey
                Intent intent = new Intent(Menu.this, PashminaKaos.class);
                startActivity(intent);
                Toast.makeText(Menu.this, "Mencari Pashmina Jersey", Toast.LENGTH_SHORT).show();
            } else {
                // Jika tidak ada kategori yang cocok
                Toast.makeText(Menu.this, "Kategori tidak ditemukan", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Jika input kosong
            Toast.makeText(Menu.this, "Harap masukkan kata kunci pencarian", Toast.LENGTH_SHORT).show();
        }
    }
}
