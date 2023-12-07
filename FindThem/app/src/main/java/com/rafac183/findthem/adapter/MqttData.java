package com.rafac183.findthem.adapter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rafac183.findthem.ui.registered_people.PeopleModel;

import java.util.ArrayList;

public class MqttData {
    static ArrayList<PeopleModel> peopleList = new ArrayList<>();
    static DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    public ArrayList<PeopleModel> getPeopleList() {
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
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return peopleList;
    }
}
