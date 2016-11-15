package com.example.trafe.dialogdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by TraFe on 11/12/2016.
 */

public class MyDialog extends DialogFragment {
    @Override
   public Dialog onCreateDialog(Bundle savedInstanceState){
        //User the Builder class because this dialog has a simple UI
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        //Dialog will have "Make a selection" as the title
        builder.setMessage("Make a selection")
        //An OK button that does nothing
        .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
              //Not implemented
            }

    })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

   return builder.create();
} } //End onCreateDialog
