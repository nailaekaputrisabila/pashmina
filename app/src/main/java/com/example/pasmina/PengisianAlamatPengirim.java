package com.example.pasmina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class PengisianAlamatPengirim extends AppCompatActivity {

    private EditText emailInput, nama, alamat, telepon;
    private Spinner provinsi, kota, kecamatan;
    private Button lanjutan;

    private HashMap<String, String[]> dataKota = new HashMap<>();
    private HashMap<String, String[]> dataKecamatan = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengisian_alamat_pengirim);

        // Inisialisasi View
        emailInput = findViewById(R.id.emailInput);
        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        telepon = findViewById(R.id.telepon);
        provinsi = findViewById(R.id.provinsi);
        kota = findViewById(R.id.kota_kabupaten);
        kecamatan = findViewById(R.id.kecamatan);
        lanjutan = findViewById(R.id.lanjutan);

        // Inisialisasi Data
        initData();

        // Set Adapter untuk Spinner Provinsi
        ArrayAdapter<String> provinsiAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataKota.keySet().toArray(new String[0]));
        provinsiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinsi.setAdapter(provinsiAdapter);

        // Listener untuk Provinsi
        provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String provinsiDipilih = (String) parent.getItemAtPosition(position);
                updateKota(provinsiDipilih);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Kosong
            }
        });

        // Listener untuk Kota
        kota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kotaDipilih = (String) parent.getItemAtPosition(position);
                updateKecamatan(kotaDipilih);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Kosong
            }
        });

        // Event Klik Tombol "Lanjutkan"
        lanjutan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil data dari input
                String email = emailInput.getText().toString().trim();
                String namaLengkap = nama.getText().toString().trim();
                String alamatLengkap = alamat.getText().toString().trim();
                String nomorTelepon = telepon.getText().toString().trim();
                String provinsiDipilih = provinsi.getSelectedItem() != null ? provinsi.getSelectedItem().toString() : "";
                String kotaDipilih = kota.getSelectedItem() != null ? kota.getSelectedItem().toString() : "";
                String kecamatanDipilih = kecamatan.getSelectedItem() != null ? kecamatan.getSelectedItem().toString() : "";

                // Validasi form
                if (email.isEmpty() || namaLengkap.isEmpty() || alamatLengkap.isEmpty() || nomorTelepon.isEmpty() ||
                        provinsiDipilih.isEmpty() || kotaDipilih.isEmpty() || kecamatanDipilih.isEmpty()) {
                    Toast.makeText(PengisianAlamatPengirim.this, "Harap isi semua data dengan lengkap!", Toast.LENGTH_SHORT).show();
                } else {
                    // Kirim data ke halaman berikutnya
                    Intent intent = new Intent(PengisianAlamatPengirim.this, Selesai.class);
                    intent.putExtra("email", email);
                    intent.putExtra("nama", namaLengkap);
                    intent.putExtra("alamat", alamatLengkap);
                    intent.putExtra("telepon", nomorTelepon);
                    intent.putExtra("provinsi", provinsiDipilih);
                    intent.putExtra("kota", kotaDipilih);
                    intent.putExtra("kecamatan", kecamatanDipilih);
                    startActivity(intent);
                }
            }
        });
    }

    private void initData() {
        // Data Provinsi dan Kota
        dataKota.put("Jawa Timur", new String[]{"Surabaya", "Malang", "Kediri", "Gresik"});
        dataKota.put("Jawa Tengah", new String[]{"Semarang", "Solo", "Purwokerto", "Magelang"});
        dataKota.put("Jawa Barat", new String[]{"Bandung", "Bogor", "Cirebon", "Bekasi"});

        // Data Kecamatan untuk Jawa Timur
        dataKecamatan.put("Surabaya", new String[]{"Rungkut", "Sukolilo", "Wonokromo", "Tegalsari"});
        dataKecamatan.put("Malang", new String[]{"Klojen", "Blimbing", "Sukun", "Lowokwaru"});
        dataKecamatan.put("Kediri", new String[]{"Mojoroto", "Pesantren", "Kota", "Gampengrejo"});
        dataKecamatan.put("Gresik", new String[]{"Kebomas", "Driyorejo", "Manyar", "Benjeng"});

        // Data Kecamatan untuk Jawa Tengah
        dataKecamatan.put("Semarang", new String[]{"Candisari", "Gajahmungkur", "Tembalang", "Pedurungan"});
        dataKecamatan.put("Solo", new String[]{"Banjarsari", "Jebres", "Laweyan", "Pasar Kliwon"});
        dataKecamatan.put("Purwokerto", new String[]{"Purwokerto Timur", "Purwokerto Selatan", "Banyumas", "Karanglewas"});
        dataKecamatan.put("Magelang", new String[]{"Mertoyudan", "Candimulyo", "Tegalrejo", "Muntilan"});

        // Data Kecamatan untuk Jawa Barat
        dataKecamatan.put("Bandung", new String[]{"Coblong", "Cibiru", "Buah Batu", "Cidadap"});
        dataKecamatan.put("Bogor", new String[]{"Bogor Barat", "Bogor Timur", "Bogor Tengah", "Tanah Sareal"});
        dataKecamatan.put("Cirebon", new String[]{"Kejaksan", "Lemahwungkuk", "Harjamukti", "Pekalipan"});
        dataKecamatan.put("Bekasi", new String[]{"Bekasi Utara", "Bekasi Barat", "Bekasi Timur", "Rawalumbu"});
    }

    private void updateKota(String provinsi) {
        String[] kotaList = dataKota.get(provinsi);
        if (kotaList != null) {
            ArrayAdapter<String> kotaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kotaList);
            kotaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            kota.setAdapter(kotaAdapter);
        }
    }

    private void updateKecamatan(String kota) {
        String[] kecamatanList = dataKecamatan.get(kota);
        if (kecamatanList != null) {
            ArrayAdapter<String> kecamatanAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kecamatanList);
            kecamatanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            kecamatan.setAdapter(kecamatanAdapter);
        }
    }

}
