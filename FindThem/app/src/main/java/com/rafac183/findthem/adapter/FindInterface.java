package com.rafac183.findthem.adapter;

import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

public interface FindInterface {
    void onCLickCV(PeopleModel peopleModel, PetsModel petsModel);
    void onClickAdd();
}
