package com.lunarekatze.catfacts.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "facts_table")
    public class FactItem {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "fact_id")
        private int fact_id;

        @ColumnInfo(name = "factstatus_verified")
        private int factstatus_verified;

        @ColumnInfo(name = "factstatus_sentcount")
        private int factstatus_sentcount;

        @Nullable
        @ColumnInfo(name = "type")
        private String type;

        @ColumnInfo(name = "deleted")
        private int deleted;

        @NonNull
        @ColumnInfo(name = "id")
        private String id;

        @Nullable
        @ColumnInfo(name = "user")
        private String user;

        @NonNull
        @ColumnInfo(name = "text")
        private String text;

        @ColumnInfo(name = "v")
        private int v;

        @Nullable
        @ColumnInfo(name = "source")
        private String source;

        @Nullable
        @ColumnInfo(name = "updatedAt")
        private String updatedAt;

        @Nullable
        @ColumnInfo(name = "createdAt")
        private String createdAt;

        @ColumnInfo(name = "used")
        private int used;

        public FactItem(int factstatus_verified, int factstatus_sentcount, @Nullable String type, int deleted, @NonNull String id, @Nullable String user, @NonNull String text, int v, @Nullable String source, @Nullable String updatedAt, @Nullable String createdAt, int used) {
            this.factstatus_verified = factstatus_verified;
            this.factstatus_sentcount = factstatus_sentcount;
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

        public int getFactstatus_verified() {
            return factstatus_verified;
        }

        public void setFactstatus_verified(int factstatus_verified) {
            this.factstatus_verified = factstatus_verified;
        }

        public int getFactstatus_sentcount() {
            return factstatus_sentcount;
        }

        public void setFactstatus_sentcount(int factstatus_sentcount) {
            this.factstatus_sentcount = factstatus_sentcount;
        }

        @Nullable
        public String getType() {
            return type;
        }

        public void setType(@Nullable String type) {
            this.type = type;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public void setId(@NonNull String id) {
            this.id = id;
        }

        @Nullable
        public String getUser() {
            return user;
        }

        public void setUser(@Nullable String user) {
            this.user = user;
        }

        @NonNull
        public String getText() {
            return text;
        }

        public void setText(@NonNull String text) {
            this.text = text;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        @Nullable
        public String getSource() {
            return source;
        }

        public void setSource(@Nullable String source) {
            this.source = source;
        }

        @Nullable
        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(@Nullable String updatedAt) {
            this.updatedAt = updatedAt;
        }

        @Nullable
        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(@Nullable String createdAt) {
            this.createdAt = createdAt;
        }

        public int getUsed() {
            return used;
        }

        public void setUsed(int used) {
            this.used = used;
        }

        public int getFact_id() {
            return fact_id;
        }

        public void setFact_id(int fact_id) {
            this.fact_id = fact_id;
        }

        @NonNull
        public String getId() {
            return id;
        }
    }
