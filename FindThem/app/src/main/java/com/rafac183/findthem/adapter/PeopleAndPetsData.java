package com.rafac183.findthem.adapter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

import java.util.ArrayList;
import java.util.Objects;

public class PeopleAndPetsData {

    static ArrayList<PeopleModel> peopleList = new ArrayList<>();
    static ArrayList<PetsModel> petsList = new ArrayList<>();
    static FirebaseFirestore authStore = FirebaseFirestore.getInstance();
    static String imgUrl;

    public static ArrayList<PeopleModel> getPeopleList() {
        peopleList.clear();
        authStore.collection("people").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (!task.getResult().isEmpty()) {
                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        String name = snapshot.get("name").toString();
                        String lastname = snapshot.get("lastname").toString();
                        String completeName = name + " " + lastname;
                        String phone = snapshot.get("phone").toString();
                        String gender = snapshot.get("gender").toString();
                        if (gender.equals("Male")) {
                            imgUrl = "https://i.ibb.co/bPkhJ1Y/hombre.png";
                        } else {
                            imgUrl = "https://i.ibb.co/qpwM05w/mujer.png";
                        }
                        peopleList.add(new PeopleModel(completeName, phone + "\n" + gender, imgUrl));
                    }
                }
            }
        });
        return peopleList;
    }
    public static ArrayList<PetsModel> getPetsList() {
        petsList.clear();
        authStore.collection("pets").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (!task.getResult().isEmpty()){
                    for (QueryDocumentSnapshot snapshot : task.getResult()){
                        String name = snapshot.get("name").toString();
                        String code = snapshot.get("code").toString();
                        String gender = snapshot.get("gender").toString();
                        if (gender.equals("Male")){
                            imgUrl = "https://i.ibb.co/87Vq8hB/lindo-perro-estudio-removebg-preview.png";
                        } else {
                            imgUrl = "https://i.ibb.co/Zc7Z5zy/adorable-cachorro-estudio-removebg-preview.png";
                        }
                        petsList.add(new PetsModel(name, code, imgUrl));
                    }
                }
            }
        });
        return petsList;
    }
}
