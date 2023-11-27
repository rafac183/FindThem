package com.rafac183.findthem.ui.registered_pets;

public class PetsModel {
    private String id;
    private String name;
    private String gender;
    private int code;
    private String image;

    public PetsModel(String id, String name, String gender, int code, String image) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.code = code;
        this.image = image;
    }

    public PetsModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

