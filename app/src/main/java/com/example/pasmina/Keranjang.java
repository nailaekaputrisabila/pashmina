package com.example.pasmina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Keranjang extends AppCompatActivity {

    private ImageView BackButton,cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang); // Ganti dengan nama file XML yang digunakan


        TextView kembaliKeHome = findViewById(R.id.lupaPassword);
        BackButton = findViewById(R.id.backButton);
        cartButton = findViewById(R.id.cartButton);

        // Tambahkan event listener untuk mengarahkan ke menu
        kembaliKeHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent untuk menuju ke halaman menu
                Intent intent = new Intent(Keranjang.this, Menu.class);
                startActivity(intent);
            }
        });
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke Slide SEBELUMNYA
                Intent intent = new Intent(Keranjang.this,Login.class);
                startActivity(intent);
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke Slide SEBELUMNYA
                Intent intent = new Intent(Keranjang.this,Keranjang.class);
                startActivity(intent);
            }
        });
    }
}
