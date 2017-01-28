package com.bnx.app.genmas;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Admin on 27/05/2016.
 */
public class ImageItem implements Parcelable {
    private byte[] image;
    private String deskripsi;

    public ImageItem(byte[] image, String deskripsi) {
        super();
        this.image = image;
        this.deskripsi = deskripsi;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    @Override
    public String toString() {
        return "ImageItem{" +
                "deskripsi='" + deskripsi + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image.length);
        dest.writeByteArray(image);
        dest.writeString(deskripsi);
    }

    public static final Creator<ImageItem> CREATOR
            = new Creator<ImageItem>() {
        public ImageItem createFromParcel(Parcel in) {
            return new ImageItem(in);
        }

        public ImageItem[] newArray(int size) {
            return new ImageItem[size];
        }
    };

    private ImageItem(Parcel in) {
        image = new byte[in.readInt()];
        in.readByteArray(image);
        deskripsi = in.readString();
    }
}
