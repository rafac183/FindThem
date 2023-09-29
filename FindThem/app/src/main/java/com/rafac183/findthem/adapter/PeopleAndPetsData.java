package com.rafac183.findthem.adapter;

import com.rafac183.findthem.ui.registered_persons.PeopleModel;

import java.util.ArrayList;

public class PeopleAndPetsData {

    static ArrayList<PeopleModel> personsList = new ArrayList<>();

    public static ArrayList<PeopleModel> getPersonsList() {
        personsList.add(new PeopleModel("Registered People", "Display registered people", "https://i.ibb.co/NyVJjTp/registered-People.png"));
        personsList.add(new PeopleModel("Registered Pets", "Display registered pets", "https://i.ibb.co/ggQPSnb/datosmascota.png"));
        personsList.add(new PeopleModel("Profile", "Change personal data", "https://i.ibb.co/c8fbs3r/datospersonales.png"));
        personsList.add(new PeopleModel("Rate Us", "Rate us, Give us your opinion!", "https://i.ibb.co/xS5V4xR/rateusicon.png"));
        personsList.add(new PeopleModel("Share", "Share our App!", "https://i.ibb.co/FqQFNBS/shareicon.png"));
        personsList.add(new PeopleModel("Settings", "Configure Application", "https://i.ibb.co/d7jJcxP/settingsicon.png"));
        return personsList;
    }
}
