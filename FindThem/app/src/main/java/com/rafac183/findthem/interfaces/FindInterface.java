package com.rafac183.findthem.interfaces;

import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

public interface FindInterface {
    void onCLickUpdate(PeopleModel personModel, PetsModel petModel);
    void onCLickDelete(PeopleModel personModel, PetsModel petModel);
    void onClickAdd();
}
