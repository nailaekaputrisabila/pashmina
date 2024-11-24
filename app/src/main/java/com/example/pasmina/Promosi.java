package com.example.pasmina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Promosi extends AppCompatActivity {
    private ImageView productImage,BackButton,cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promosi);

        productImage = findViewById(R.id.productImage);
        BackButton = findViewById(R.id.backButton);
        cartButton = findViewById(R.id.cartButton);

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Promosi.this,Menu.class);
                startActivity(intent);
            }
        });
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Promosi.this,Login.class);
                startActivity(intent);
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Promosi.this,Keranjang.class);
                startActivity(intent);
            }
        });
    }
}
