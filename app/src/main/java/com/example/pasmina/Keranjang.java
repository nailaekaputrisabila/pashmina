package com.example.pasmina;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Keranjang extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharAdapter charAdapter;
    private List<Produk> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        loadCartItems();

        if (productList.isEmpty()) {
            Toast.makeText(this, "Keranjang Anda kosong!", Toast.LENGTH_SHORT).show();
        }

        charAdapter = new CharAdapter(productList);
        charAdapter.setOnItemActionListener(new CharAdapter.OnItemActionListener() {
            @Override
            public void onRemoveItem(int position) {
                charAdapter.removeItem(position);
                saveCartItems();
                Toast.makeText(Keranjang.this, "Item dihapus dari keranjang", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onQuantityChanged(int position, int newQuantity) {
                updateItemQuantity(position, newQuantity);
            }

            @Override
            public void onCheckout() {
                onCheckoutProcess(); // Menangani checkout
            }
        });

        recyclerView.setAdapter(charAdapter);
    }

    private void loadCartItems() {
        SharedPreferences sharedPreferences = getSharedPreferences("keranjang", MODE_PRIVATE);
        String cartItems = sharedPreferences.getString("cart_items", "");

        if (cartItems != null && !cartItems.isEmpty()) {
            String[] items = cartItems.split("\n");
            for (String item : items) {
                try {
                    String[] details = item.split("\\|");
                    String name = details[0];
                    int quantity = Integer.parseInt(details[1]);
                    String color = details[2];
                    int imageResId = Integer.parseInt(details[3]);

                    productList.add(new Produk(name, color, imageResId, quantity));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveCartItems() {
        SharedPreferences sharedPreferences = getSharedPreferences("keranjang", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        StringBuilder updatedCart = new StringBuilder();
        for (Produk produk : productList) {
            String item = produk.getName() + "|" + produk.getQuantity() + "|" + produk.getColor() + "|" + produk.getImageResId();
            if (updatedCart.length() > 0) {
                updatedCart.append("\n");
            }
            updatedCart.append(item);
        }

        editor.putString("cart_items", updatedCart.toString());
        editor.apply();
    }

    private void updateItemQuantity(int position, int newQuantity) {
        if (position >= 0 && position < productList.size() && newQuantity > 0) {
            productList.get(position).setQuantity(newQuantity);
            charAdapter.notifyItemChanged(position);
            saveCartItems();
        } else if (newQuantity <= 0) {
            charAdapter.removeItem(position);
            saveCartItems();
        }
    }

    // Fungsi checkout
    private void onCheckoutProcess() {
        if (productList.isEmpty()) {
            Toast.makeText(Keranjang.this, "Keranjang Anda kosong, tidak bisa checkout!", Toast.LENGTH_SHORT).show();
        } else {
            // Menampilkan detail pesanan
            StringBuilder orderDetails = new StringBuilder();
            for (Produk produk : productList) {
                orderDetails.append(produk.getName())
                        .append(" - Qty: ")
                        .append(produk.getQuantity())
                        .append("\n");

            }
            Toast.makeText(Keranjang.this, orderDetails.toString(), Toast.LENGTH_LONG).show();

            // Simulasi checkout berhasil
            Toast.makeText(Keranjang.this, "Pesanan Anda berhasil diproses!", Toast.LENGTH_SHORT).show();

            // Mengosongkan keranjang
            productList.clear();
            charAdapter.notifyDataSetChanged();
            saveCartItems();

            // Navigasi ke konfirmasi pesanan jika perlu
            Intent intent = new Intent(Keranjang.this, Checkout.class);
            startActivity(intent);
        }
    }
}