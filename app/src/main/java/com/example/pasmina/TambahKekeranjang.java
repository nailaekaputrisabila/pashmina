package com.example.pasmina;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TambahKekeranjang extends AppCompatActivity {

    private ImageView backButton, cartButton, productImage;
    private Button btnDarkGrey, btnHitam, btnNavy, btnMilo, btnSilver, btnTan;
    private Button btnBuyNow, btnAddToCart;
    private TextView headerTitle, quantityText;
    private int quantity = 1; // Default quantity
    private String selectedColor = "Default"; // Default color

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kekeranjang);

        // Initialize Views
        backButton = findViewById(R.id.backButton);
        cartButton = findViewById(R.id.cartButton);
        productImage = findViewById(R.id.productImage);
        headerTitle = findViewById(R.id.headerTitle);
        quantityText = findViewById(R.id.quantityText);

        btnDarkGrey = findViewById(R.id.btnDarkGrey);
        btnHitam = findViewById(R.id.btnHitam);
        btnNavy = findViewById(R.id.btnNavy);
        btnMilo = findViewById(R.id.btnMilo);
        btnSilver = findViewById(R.id.btnSilver);
        btnTan = findViewById(R.id.btnTan);

        btnBuyNow = findViewById(R.id.btnBuyNow);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        backButton.setOnClickListener(v -> onBackPressed());

        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(TambahKekeranjang.this, Keranjang.class);
            startActivity(intent);
        });

        btnDarkGrey.setOnClickListener(v -> {
            productImage.setImageResource(R.drawable.img_5); // Example image
            selectedColor = "Dark Grey";
        });
        btnHitam.setOnClickListener(v -> {
            productImage.setImageResource(R.drawable.img_6); // Example image
            selectedColor = "Hitam";
        });
        btnNavy.setOnClickListener(v -> {
            productImage.setImageResource(R.drawable.img_4); // Example image
            selectedColor = "Navy";
        });
        btnMilo.setOnClickListener(v -> {
            productImage.setImageResource(R.drawable.img_7); // Example image
            selectedColor = "Milo";
        });
        btnSilver.setOnClickListener(v -> {
            productImage.setImageResource(R.drawable.img_15); // Example image
            selectedColor = "Silver";
        });
        btnTan.setOnClickListener(v -> {
            productImage.setImageResource(R.drawable.img_16); // Example image
            selectedColor = "Tan";
        });

        btnBuyNow.setOnClickListener(v -> {
            Intent intent = new Intent(TambahKekeranjang.this, Checkout.class);
            intent.putExtra("product_name", "Produk Pashmina");
            intent.putExtra("quantity", quantity);
            intent.putExtra("color", selectedColor);
            intent.putExtra("price", 20000); // Harga satuan
            startActivity(intent);
        });

        btnAddToCart.setOnClickListener(v -> {
            saveProductToCart("Produk Pashmina", quantity, selectedColor, R.drawable.img_5); // Example image
            Toast.makeText(this, "Ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TambahKekeranjang.this, Keranjang.class);
            startActivity(intent);
        });

        findViewById(R.id.decreaseButton).setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateQuantity();
            }
        });

        findViewById(R.id.increaseButton).setOnClickListener(v -> {
            quantity++;
            updateQuantity();
        });
    }

    private void updateQuantity() {
        quantityText.setText(String.valueOf(quantity));
    }

    private void saveProductToCart(String productName, int quantity, String color, int imageResId) {
        SharedPreferences sharedPreferences = getSharedPreferences("keranjang", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String existingCart = sharedPreferences.getString("cart_items", "");
        String newProduct = productName + "|" + quantity + "|" + color + "|" + imageResId;

        String updatedCart = existingCart.isEmpty() ? newProduct : existingCart + "\n" + newProduct;
        editor.putString("cart_items", updatedCart);
        editor.apply();
    }
}
