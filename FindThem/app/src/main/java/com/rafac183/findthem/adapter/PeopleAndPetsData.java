package com.rafac183.findthem.adapter;

import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

import java.util.ArrayList;

public class PeopleAndPetsData {

    static ArrayList<PeopleModel> peopleList = new ArrayList<>();
    static ArrayList<PetsModel> petsList = new ArrayList<>();

    public static ArrayList<PeopleModel> getPeopleList() {
        peopleList.clear();
        peopleList.add(new PeopleModel("Person 1", "Description 1", "https://i.ibb.co/NyVJjTp/registered-People.png"));
        peopleList.add(new PeopleModel("Person 2", "Description 2", "https://i.ibb.co/ggQPSnb/datosmascota.png"));
        peopleList.add(new PeopleModel("Person 3", "Description 3", "https://i.ibb.co/c8fbs3r/datospersonales.png"));
        peopleList.add(new PeopleModel("Person 4", "Description 4", "https://i.ibb.co/xS5V4xR/rateusicon.png"));
        peopleList.add(new PeopleModel("Person 5", "Description 5", "https://i.ibb.co/FqQFNBS/shareicon.png"));
        peopleList.add(new PeopleModel("Person 6", "Description 6", "https://i.ibb.co/d7jJcxP/settingsicon.png"));
        return peopleList;
    }
    public static ArrayList<PetsModel> getPetsList() {
        petsList.clear();
        petsList.add(new PetsModel("Pet 1", "Description 1", "https://i.ibb.co/NyVJjTp/registered-People.png"));
        petsList.add(new PetsModel("Pet 2", "Description 2", "https://i.ibb.co/ggQPSnb/datosmascota.png"));
        petsList.add(new PetsModel("Pet 3", "Description 3", "https://i.ibb.co/c8fbs3r/datospersonales.png"));
        petsList.add(new PetsModel("Pet 4", "Description 4", "https://i.ibb.co/xS5V4xR/rateusicon.png"));
        petsList.add(new PetsModel("Pet 5", "Description 5", "https://i.ibb.co/FqQFNBS/shareicon.png"));
        petsList.add(new PetsModel("Pet 6", "Description 6", "https://i.ibb.co/d7jJcxP/settingsicon.png"));
        return petsList;
    }
}
