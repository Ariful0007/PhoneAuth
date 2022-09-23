package com.meass.professionalworks;

public class Contact {
    int id;
    String name, batch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Contact(int id, String name, String batch) {
        this.id = id;
        this.name = name;
        this.batch = batch;
    }

    public Contact() {
    }
}
