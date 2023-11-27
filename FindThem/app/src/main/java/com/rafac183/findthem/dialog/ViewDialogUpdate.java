package com.rafac183.findthem.dialog;

import static android.content.Intent.getIntent;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.AlertDialogPersonBinding;
import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

public class ViewDialogUpdate implements AdapterView.OnItemSelectedListener{
    DatabaseReference reference = null;
    Spinner sGender;
    String[] genders = {"Choose one", "Male", "Female"};

    public void showDialogUpdate(Context context, String id, String name, String lastname, String phone, String gender, Integer code){
        reference = FirebaseDatabase.getInstance().getReference();

        if (code == null || code == 0){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_person);

            EditText textName = dialog.findViewById(R.id.edNameCV);
            EditText textLastName = dialog.findViewById(R.id.edLastNameCV);
            sGender = dialog.findViewById(R.id.spinnerGenderCV);
            EditText textPhone = dialog.findViewById(R.id.edPhoneCV);
            Spinner(context);
            int gen = 0;
            String gen2 = null;

            textName.setText(name);
            textLastName.setText(lastname);
            textPhone.setText(phone);
            for(int i = 0; i < genders.length; i++){
                if (genders[i].equals(gender)){
                    gen = i;
                    gen2 = genders[i];
                }
            }
            sGender.setSelection(gen);

            Button buttonUpdate = dialog.findViewById(R.id.buttonUpdate);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonCancel.setOnClickListener(view -> dialog.dismiss());

            String finalGen = gen2;
            buttonUpdate.setOnClickListener(view -> {

                String newName = textName.getText().toString().trim();
                String newLastName = textLastName.getText().toString().trim();
                String newPhone = textPhone.getText().toString().trim();
                String selectedGender = sGender.getSelectedItem().toString();
                String img;

                if (name.isEmpty() || lastname.isEmpty() || phone.isEmpty() || gender == null) {
                    Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                } else {

                    if (newName.equals(name) && newLastName.equals(lastname) && newPhone.equals(phone) && selectedGender.equals(finalGen)) {
                        Toast.makeText(context, "You don't change anything", Toast.LENGTH_SHORT).show();
                    } else {
                        if (selectedGender.equals("Male")){
                            img = "https://i.ibb.co/bPkhJ1Y/hombre.png";
                        } else {
                            img = "https://i.ibb.co/qpwM05w/mujer.png";
                        }
                        reference.child("People").child(id).setValue(new PeopleModel(id, newName, newLastName, newPhone, selectedGender, img));
                        Toast.makeText(context, "Person Updated successfully!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "Please restart the tab!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }


                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        } else {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_pet);

            EditText textName = dialog.findViewById(R.id.edNamePetCV);
            sGender = dialog.findViewById(R.id.spinnerGenderCV);
            EditText textCode = dialog.findViewById(R.id.edCodePetCV);
            Spinner(context);
            int gen = 0;
            String gen2 = null;

            textName.setText(name);
            textCode.setText(String.valueOf(code));
            for(int i = 0; i < genders.length; i++){
                if (genders[i].equals(gender)){
                    gen = i;
                    gen2 = genders[i];
                }
            }
            sGender.setSelection(gen);

            Button buttonUpdate = dialog.findViewById(R.id.buttonUpdate);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonCancel.setOnClickListener(view -> dialog.dismiss());

            String finalGen = gen2;
            buttonUpdate.setOnClickListener(view -> {

                String newName = textName.getText().toString();
                Integer newCode = textCode.getText().hashCode();
                String selectedGender = sGender.getSelectedItem().toString();
                String img;

                if (name.isEmpty() || code == null || gender == null) {
                    Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                } else {

                    if (newName.equals(name) && newCode.equals(code) && selectedGender.equals(finalGen)) {
                        Toast.makeText(context, "You don't change anything", Toast.LENGTH_SHORT).show();
                    } else {
                        if (selectedGender.equals("Male")){
                            img = "https://i.ibb.co/87Vq8hB/lindo-perro-estudio-removebg-preview.png";
                        } else {
                            img = "https://i.ibb.co/Zc7Z5zy/adorable-cachorro-estudio-removebg-preview.png";
                        }
                        reference.child("Pets").child(id).setValue(new PetsModel(id, newName, selectedGender, newCode, img));
                        Toast.makeText(context, "Pet Updated successfully!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "Please restart the tab!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }


                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }
    private void Spinner(Context context){
        sGender.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(context, android.R.layout.simple_spinner_item, genders);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sGender.setAdapter(aa);
        sGender.setSelection(0, false);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
