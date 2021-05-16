package com.lunarekatze.catfacts;

import android.os.Parcel;
import android.os.Parcelable;

public class Fact implements Parcelable {

    private FactStatus status;
    private String type;
    private Boolean deleted;
    private String id;
    private String user;
    private String text;
    private Integer v;
    private String source;
    private String updatedAt;
    private String createdAt;
    private Boolean used;

    public Fact(FactStatus status, String type, Boolean deleted, String id, String user, String text, Integer v, String source, String updatedAt, String createdAt, Boolean used) {
        this.status = status;
        this.type = type;
        this.deleted = deleted;
        this.id = id;
        this.user = user;
        this.text = text;
        this.v = v;
        this.source = source;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.used = used;
    }

    protected Fact(Parcel in) {
        type = in.readString();
        byte tmpDeleted = in.readByte();
        deleted = tmpDeleted == 0 ? null : tmpDeleted == 1;
        id = in.readString();
        user = in.readString();
        text = in.readString();
        if (in.readByte() == 0) {
            v = null;
        } else {
            v = in.readInt();
        }
        source = in.readString();
        updatedAt = in.readString();
        createdAt = in.readString();
        byte tmpUsed = in.readByte();
        used = tmpUsed == 0 ? null : tmpUsed == 1;
    }

    public static final Creator<Fact> CREATOR = new Creator<Fact>() {
        @Override
        public Fact createFromParcel(Parcel in) {
            return new Fact(in);
        }

        @Override
        public Fact[] newArray(int size) {
            return new Fact[size];
        }
    };

    public FactStatus getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public Integer getV() {
        return v;
    }

    public String getSource() {
        return source;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Boolean getUsed() {
        return used;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeByte((byte) (deleted == null ? 0 : deleted ? 1 : 2));
        parcel.writeString(id);
        parcel.writeString(user);
        parcel.writeString(text);
        if (v == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(v);
        }
        parcel.writeString(source);
        parcel.writeString(updatedAt);
        parcel.writeString(createdAt);
        parcel.writeByte((byte) (used == null ? 0 : used ? 1 : 2));
    }

    private class FactStatus{
        private Boolean verified;
        private Integer sentCount;
    }
}
