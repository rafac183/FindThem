package com.rafac183.findthem.adapter;

import android.os.Build;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.rafac183.findthem.model.PersonModel;
import com.rafac183.findthem.model.PetModel;
import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class PeopleAndPetsData {

    static ArrayList<PeopleModel> peopleList = new ArrayList<>();
    static ArrayList<PetsModel> petsList = new ArrayList<>();
    static CompletableFuture<ArrayList<PeopleModel>> futurePeople;
    static CompletableFuture<ArrayList<PetsModel>> futurePets;
    static FirebaseFirestore authStore = FirebaseFirestore.getInstance();
    static DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    static String imgUrl;

    public static CompletableFuture<ArrayList<PeopleModel>> getPeopleList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            futurePeople = new CompletableFuture<>();
        }
        reference.child("People").orderByChild("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                peopleList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PersonModel personModel = dataSnapshot.getValue(PersonModel.class);
                    if (personModel != null) {
                        String name = personModel.getName();
                        String lastname = personModel.getLastname();
                        String completeName = name + " " + lastname;
                        String phone = personModel.getPhone();
                        String gender = personModel.getGender();
                        String imgUrl = gender.equals("Male") ? "https://i.ibb.co/bPkhJ1Y/hombre.png" : "https://i.ibb.co/qpwM05w/mujer.png";
                        peopleList.add(new PeopleModel(completeName, "Phone: " + phone + "\nGender: " + gender, imgUrl));
                    }
                }
                if (peopleList.isEmpty()) {
                    // Devolver una lista vacía si no hay resultados
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        futurePeople.complete(new ArrayList<>());
                    }
                } else {
                    // Completar el CompletableFuture con la lista de personas
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        futurePeople.complete(peopleList);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    futurePeople.completeExceptionally(new RuntimeException("Error en la consulta a la base de datos"));
                }
            }
        });
        return futurePeople;
    }
    public static CompletableFuture<ArrayList<PetsModel>> getPetsList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            futurePets = new CompletableFuture<>();
        }
        reference.child("Pets").orderByChild("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                petsList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PetModel personModel = dataSnapshot.getValue(PetModel.class);
                    if (personModel != null) {
                        String name = personModel.getName();
                        Integer code = personModel.getCode();
                        String gender = personModel.getGender();
                        String imgUrl = gender.equals("Male") ? "https://i.ibb.co/87Vq8hB/lindo-perro-estudio-removebg-preview.png" : "https://i.ibb.co/Zc7Z5zy/adorable-cachorro-estudio-removebg-preview.png";
                        petsList.add(new PetsModel(name, "Code: " + code, imgUrl));
                    }
                }
                if (petsList.isEmpty()) {
                    // Devolver una lista vacía si no hay resultados
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        futurePets.complete(new ArrayList<>());
                    }
                } else {
                    // Completar el CompletableFuture con la lista de personas
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        futurePets.complete(petsList);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    futurePets.completeExceptionally(new RuntimeException("Error en la consulta a la base de datos"));
                }
            }
        });
        return futurePets;
    }
}
