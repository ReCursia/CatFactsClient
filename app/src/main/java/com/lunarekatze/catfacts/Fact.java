package com.lunarekatze.catfacts;

public class Fact {

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

    private class FactStatus{
        private Boolean verified;
        private Integer sentCount;
    }
}
