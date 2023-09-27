package com.rafac183.findthem.ui.home;

import java.util.ArrayList;

public class MyData {

    static ArrayList<HomeModel> homeList = new ArrayList<>();

    public static ArrayList<HomeModel> getHomeList() {
        homeList.add(new HomeModel("Datos Personales", "Modificar Datos Personales", "https://i.ibb.co/c8fbs3r/datospersonales.png"));
        homeList.add(new HomeModel("Datos Mascota", "Modificar Datos Mascota", "https://i.ibb.co/ggQPSnb/datosmascota.png"));
        return homeList;
    }
}
