package com.example.pasmina;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CharAdapter extends RecyclerView.Adapter<CharAdapter.ViewHolder> {

    private List<Produk> productList;
    private OnItemActionListener listener;

    public CharAdapter(List<Produk> productList) {
        this.productList = productList;
    }

    public interface OnItemActionListener {
        void onQuantityChanged(int position, int newQuantity);
        void onRemoveItem(int position);
        void onCheckout(); // Menambahkan listener untuk checkout
    }

    public void setOnItemActionListener(OnItemActionListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_char_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produk produk = productList.get(position);

        holder.nameTextView.setText(produk.getName());
        holder.colorTextView.setText("Warna: " + produk.getColor());
        holder.quantityTextView.setText("Jumlah: " + produk.getQuantity());
        holder.productImageView.setImageResource(produk.getImageResId());

        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) listener.onRemoveItem(position);
        });

        // Menangani klik tombol Checkout
        holder.checkoutButton.setOnClickListener(v -> {
            if (listener != null) listener.onCheckout(); // Panggil listener checkout
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void removeItem(int position) {
        if (position >= 0 && position < productList.size()) {
            productList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, colorTextView, quantityTextView;
        ImageView productImageView;
        Button deleteButton, checkoutButton; // Tombol Checkout

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            colorTextView = itemView.findViewById(R.id.colorTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            productImageView = itemView.findViewById(R.id.productImageView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            checkoutButton = itemView.findViewById(R.id.checkoutButton); // Tombol Checkout
        }
    }
}
