package com.example.pasmina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Checkout extends AppCompatActivity {

    private TextView productName, productQuantity, productColor, productPrice;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Initialize views
        productName = findViewById(R.id.productName);
        productQuantity = findViewById(R.id.productQuantity);
        productColor = findViewById(R.id.productColor);
        productPrice = findViewById(R.id.productPrice);
        confirmButton = findViewById(R.id.confirmButton);
        ImageView backButton = findViewById(R.id.backButton);

        // Receive data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("product_name");
        int quantity = intent.getIntExtra("quantity", 1);
        String color = intent.getStringExtra("color");
        int price = intent.getIntExtra("price", 0);

        // Set values to views
        productName.setText("Nama Produk: " + name);
        productQuantity.setText("Jumlah: " + quantity);
        productColor.setText("Warna: " + color);
        productPrice.setText("Harga: Rp " + (price * quantity));

        // Handle confirm button click
        confirmButton.setOnClickListener(v -> {
            // Navigate to Pembayaran Activity
            Intent paymentIntent = new Intent(Checkout.this, Pembayaran.class);
            paymentIntent.putExtra("product_name", name);
            paymentIntent.putExtra("quantity", quantity);
            paymentIntent.putExtra("color", color);
            paymentIntent.putExtra("price", price * quantity); // Total price
            startActivity(paymentIntent);
        });

        // Handle back button click
        backButton.setOnClickListener(v -> {
            Toast.makeText(Checkout.this, "Kembali ke halaman Tambah Keranjang", Toast.LENGTH_SHORT).show();
            Intent intentBack = new Intent(Checkout.this, TambahKekeranjang.class);
            startActivity(intentBack);
            finish(); // Ensure no duplicate activities in the stack
        });
    }
}
