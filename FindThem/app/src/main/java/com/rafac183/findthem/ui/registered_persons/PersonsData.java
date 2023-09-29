package com.rafac183.findthem.ui.registered_persons;

import com.rafac183.findthem.ui.home.HomeModel;

import java.util.ArrayList;

public class PersonsData {

    static ArrayList<PersonsModel> personsList = new ArrayList<>();

    public static ArrayList<PersonsModel> getPersonsList() {
        personsList.add(new PersonsModel("Registered Persons", "Display registered persons", "https://i.ibb.co/NyVJjTp/registered-People.png"));
        personsList.add(new PersonsModel("Registered Pets", "Display registered pets", "https://i.ibb.co/ggQPSnb/datosmascota.png"));
        personsList.add(new PersonsModel("Profile", "Change personal data", "https://i.ibb.co/c8fbs3r/datospersonales.png"));
        personsList.add(new PersonsModel("Rate Us", "Rate us, Give us your opinion!", "https://i.ibb.co/xS5V4xR/rateusicon.png"));
        personsList.add(new PersonsModel("Share", "Share our App!", "https://i.ibb.co/FqQFNBS/shareicon.png"));
        personsList.add(new PersonsModel("Settings", "Configure Application", "https://i.ibb.co/d7jJcxP/settingsicon.png"));
        return personsList;
    }
}
