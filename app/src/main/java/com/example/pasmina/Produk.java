package com.example.pasmina;

import android.os.Parcel;
import android.os.Parcelable;

public class Produk implements Parcelable {
    private String name;
    private String color;
    private int imageResId;  // This represents the price
    private int quantity;

    // Constructor
    public Produk(String name, String color, int imageResId, int quantity) {
        this.name = name;
        this.color = color;
        this.imageResId = imageResId;
        this.quantity = quantity;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Parcelable implementation
    protected Produk(Parcel in) {
        name = in.readString();
        color = in.readString();
        imageResId = in.readInt();
        quantity = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(color);
        dest.writeInt(imageResId);
        dest.writeInt(quantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Produk> CREATOR = new Creator<Produk>() {
        @Override
        public Produk createFromParcel(Parcel in) {
            return new Produk(in);
        }

        @Override
        public Produk[] newArray(int size) {
            return new Produk[size];
        }
    };
}
