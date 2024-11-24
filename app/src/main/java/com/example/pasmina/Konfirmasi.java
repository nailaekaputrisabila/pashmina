package com.example.pasmina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Konfirmasi extends AppCompatActivity {

    private RadioGroup paymentMethodGroup;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);

        // Inisialisasi Views
        paymentMethodGroup = findViewById(R.id.paymentMethodGroup);
        confirmButton = findViewById(R.id.confirmButton);

        // Tombol Konfirmasi
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = paymentMethodGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    // Jika belum memilih metode pembayaran
                    Toast.makeText(Konfirmasi.this, "Silakan pilih metode pembayaran!", Toast.LENGTH_SHORT).show();
                } else {
                    // Ambil metode pembayaran yang dipilih
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String selectedPaymentMethod = selectedRadioButton.getText().toString();

                    // Tampilkan metode pembayaran yang dipilih
                    Toast.makeText(Konfirmasi.this, "Metode pembayaran: " + selectedPaymentMethod, Toast.LENGTH_SHORT).show();

                    // Kirim data ke halaman berikutnya (jika ada)
                    Intent intent = new Intent(Konfirmasi.this, PengisianAlamatPengirim.class);
                    intent.putExtra("selectedPaymentMethod", selectedPaymentMethod);
                    startActivity(intent);
                }
            }
        });
    }
}
