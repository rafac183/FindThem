package com.rafac183.findthem.ui.home;

import java.util.ArrayList;

public class HomeData {

    static ArrayList<HomeModel> homeList = new ArrayList<>();

    public static ArrayList<HomeModel> getHomeList() {
        homeList.clear();
        homeList.add(new HomeModel("Welcome!", "Discover what you can do with our app!", "https://i.ibb.co/1RxrWgp/user.png"));
        homeList.add(new HomeModel("Profile", "Change personal data", "https://i.ibb.co/c8fbs3r/datospersonales.png"));
        homeList.add(new HomeModel("Rate Us", "Rate us, Give us your opinion!", "https://i.ibb.co/xS5V4xR/rateusicon.png"));
        homeList.add(new HomeModel("Share", "Share our App!", "https://i.ibb.co/FqQFNBS/shareicon.png"));
        homeList.add(new HomeModel("Settings", "Configure Application", "https://i.ibb.co/d7jJcxP/settingsicon.png"));
        return homeList;
    }
}
