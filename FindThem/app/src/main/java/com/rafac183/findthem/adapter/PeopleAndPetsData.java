package com.rafac183.findthem.adapter;

import android.os.Build;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class PeopleAndPetsData {

    static ArrayList<PeopleModel> peopleList = new ArrayList<>();
    static ArrayList<PetsModel> petsList = new ArrayList<>();
    static CompletableFuture<ArrayList<PeopleModel>> futurePeople;
    static CompletableFuture<ArrayList<PetsModel>> futurePets;
    static DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public static CompletableFuture<ArrayList<PeopleModel>> getPeopleList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            futurePeople = new CompletableFuture<>();
        }
        reference.child("People").orderByChild("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                peopleList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PeopleModel personModel = dataSnapshot.getValue(PeopleModel.class);
                    if (personModel != null) {
                        String id = personModel.getId();
                        String name = personModel.getName();
                        String lastname = personModel.getLastname();
                        String phone = personModel.getPhone();
                        String gender = personModel.getGender();
                        String imgUrl = personModel.getImage();
                        peopleList.add(new PeopleModel(id, name, lastname, phone, gender, imgUrl));
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
                    PetsModel petsModel = dataSnapshot.getValue(PetsModel.class);
                    if (petsModel != null) {
                        String id = petsModel.getId();
                        String name = petsModel.getName();
                        int code = petsModel.getCode();
                        String gender = petsModel.getGender();
                        String imgUrl = petsModel.getImage();
                        petsList.add(new PetsModel(id, name, gender, code, imgUrl));
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
