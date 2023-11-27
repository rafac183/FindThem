package com.rafac183.findthem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rafac183.findthem.R;

public class ViewDialogConfirmDelete {
    DatabaseReference reference = null;
    public void showDialogDelete(Context context, String id, Integer code) {
        reference = FirebaseDatabase.getInstance().getReference();

        if (code == null || code == 0){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_confirm_delete);

            Button buttonDelete = dialog.findViewById(R.id.buttonDelete);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonCancel.setOnClickListener(view -> dialog.dismiss());

            buttonDelete.setOnClickListener(view -> {

                reference.child("People").child(id).removeValue();
                Toast.makeText(context, "Person Deleted successfully!", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Please restart the tab!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        } else {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_confirm_delete);

            Button buttonDelete = dialog.findViewById(R.id.buttonDelete);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonCancel.setOnClickListener(view -> dialog.dismiss());

            buttonDelete.setOnClickListener(view -> {

                reference.child("Pets").child(id).removeValue();
                Toast.makeText(context, "Pet Deleted successfully!", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Please restart the tab!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }

    }
}
