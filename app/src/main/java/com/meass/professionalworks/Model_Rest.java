package com.meass.professionalworks;

public class Model_Rest {
    String name,imageurl,description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Model_Rest(String name, String imageurl, String description) {
        this.name = name;
        this.imageurl = imageurl;
        this.description = description;
    }

    public Model_Rest() {
    }
}
