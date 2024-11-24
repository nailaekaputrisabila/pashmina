package com.example.pasmina;

import android.content.Intent;
import android.os.Bundle;
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

        searchIcon.setOnClickListener(v -> {
            String query = searchEditText.getText().toString().trim();
            if (!query.isEmpty()) {

                Toast.makeText(Menu.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Menu.this, "Please enter a search term", Toast.LENGTH_SHORT).show();
            }
        });
        loginTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, Login.class);
            startActivity(intent);
        });
        shopTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, PashminaKaos.class);
            startActivity(intent);
        });


    }
}
