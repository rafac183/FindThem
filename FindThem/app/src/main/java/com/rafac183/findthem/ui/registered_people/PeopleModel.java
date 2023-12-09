package com.rafac183.findthem.ui.registered_people;

public class PeopleModel {
    private String id;
    private String name;
    private String lastname;
    private String gender;
    private String phone;
    private String image;

    public PeopleModel(String id, String name, String lastname, String phone, String gender, String image) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.gender = gender;
        this.image = image;
    }

    public PeopleModel() {
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    private boolean isActivated;

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}

