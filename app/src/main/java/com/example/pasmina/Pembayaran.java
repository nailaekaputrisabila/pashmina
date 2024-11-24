package com.example.pasmina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pembayaran extends AppCompatActivity {

    private RadioGroup metodePengirimanGroup;
    private Button kembaliButton, lanjutkanButton;
    private TextView subtotalTextView, productDetailsTextView;
    private TextView productName, productQuantity, productColor, productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        // Inisialisasi Views
        metodePengirimanGroup = findViewById(R.id.metodePengirimanGroup);
        kembaliButton = findViewById(R.id.kembaliButton);
        lanjutkanButton = findViewById(R.id.lanjutkanButton);
        subtotalTextView = findViewById(R.id.subtotalTextView);
        productName = findViewById(R.id.productName);
        productQuantity = findViewById(R.id.productQuantity);
        productColor = findViewById(R.id.productColor);
        productPrice = findViewById(R.id.productPrice);

        // Ambil data dari intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("product_name");
        int quantity = intent.getIntExtra("quantity", 1);
        String color = intent.getStringExtra("color");
        int totalPrice = intent.getIntExtra("price", 0);

        // Tampilkan detail produk
        productName.setText("Nama Produk: " + name);
        productQuantity.setText("Jumlah: " + quantity);
        productColor.setText("Warna: " + color);
        productPrice.setText("Harga: Rp " + totalPrice);

        // Set subtotal (nilai total harga produk)
        subtotalTextView.setText("Subtotal: Rp " + totalPrice);

        // Tombol Kembali
        kembaliButton.setOnClickListener(v -> finish());

        // Tombol Lanjutkan
        lanjutkanButton.setOnClickListener(v -> {
            int selectedId = metodePengirimanGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(Pembayaran.this, "Silakan pilih metode pengiriman terlebih dahulu!", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton selectedRadioButton = findViewById(selectedId);
                String metodePengiriman = selectedRadioButton.getText().toString();

                // Kirim data ke halaman Konfirmasi
                Intent konfirmasiIntent = new Intent(Pembayaran.this,Konfirmasi.class);
                konfirmasiIntent.putExtra("product_name", name);
                konfirmasiIntent.putExtra("quantity", quantity);
                konfirmasiIntent.putExtra("color", color);
                konfirmasiIntent.putExtra("price", totalPrice);
                konfirmasiIntent.putExtra("metodePengiriman", metodePengiriman);
                startActivity(konfirmasiIntent);
            }
        });

    }
}
