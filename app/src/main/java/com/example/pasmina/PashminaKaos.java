package com.example.pasmina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PashminaKaos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pashmina_kaos);

        // Inisialisasi view dari XML
        ImageView backButton = findViewById(R.id.backButton);
        ImageView cartButton = findViewById(R.id.cartButton);
        Spinner categorySpinner = findViewById(R.id.category_spinner);
        ImageView btnNavy = findViewById(R.id.btnNavy);
        ImageView btnDarkGrey = findViewById(R.id.btnDarkGrey);
        ImageView btnBlack = findViewById(R.id.btnBlack);
        ImageView btnMilo = findViewById(R.id.btnMilo);

        // Data kategori
        String[] categories = {"Hijab", "Pashmina Kaos", "Pashmina Jersey"};

        // Adapter untuk Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Listener untuk Spinner
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories[position];
                Toast.makeText(PashminaKaos.this, "Kategori dipilih: " + selectedCategory, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Tidak melakukan apa-apa
            }
        });

        // Fungsi tombol back
        backButton.setOnClickListener(v -> {
            Toast.makeText(PashminaKaos.this, "Kembali ke halaman sebelumnya", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PashminaKaos.this, Menu.class);
            startActivity(intent); // Changed finish() to startActivity for consistency
        });

        // Fungsi tombol keranjang
        cartButton.setOnClickListener(v -> {
            Toast.makeText(PashminaKaos.this, "Membuka halaman keranjang", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PashminaKaos.this, Keranjang.class);
            startActivity(intent);
        });

        // Fungsi untuk tombol produk (btnNavy)
        btnNavy.setOnClickListener(v -> {
            Toast.makeText(PashminaKaos.this, "Menambah Navy ke keranjang", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PashminaKaos.this, TambahKekeranjang.class);
            startActivity(intent);
        });

        // Fungsi untuk tombol produk lainnya
        btnDarkGrey.setOnClickListener(v -> {
            Toast.makeText(PashminaKaos.this, "Menambah Dark Grey ke keranjang", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PashminaKaos.this, TambahKekeranjang.class);
            startActivity(intent);
        });

        btnBlack.setOnClickListener(v -> {
            Toast.makeText(PashminaKaos.this, "Menambah Hitam ke keranjang", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PashminaKaos.this, TambahKekeranjang.class);
            startActivity(intent);
        });

        btnMilo.setOnClickListener(v -> {
            Toast.makeText(PashminaKaos.this, "Menambah Milo ke keranjang", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PashminaKaos.this, TambahKekeranjang.class);
            startActivity(intent);
        });
    }
}
