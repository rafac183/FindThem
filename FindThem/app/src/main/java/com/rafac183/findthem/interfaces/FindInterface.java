package com.rafac183.findthem.interfaces;

import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

public interface FindInterface {
    void onCLickUpdate(PeopleModel peopleModel, PetsModel petsModel);
    void onCLickDelete(PeopleModel peopleModel, PetsModel petsModel);
    void onClickAdd();
}
