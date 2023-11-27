package com.rafac183.findthem.model;

public class PetModel {
    String id;
    String name;
    String gender;
    Integer code;

    public PetModel(String id, String name, String gender, Integer code) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.code = code;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
