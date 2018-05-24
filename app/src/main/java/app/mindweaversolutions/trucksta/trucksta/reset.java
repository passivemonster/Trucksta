package app.mindweaversolutions.trucksta.trucksta;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Toast;

/**
 * Created by john on 21/5/18.
 */

public class reset extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()) ;
        LayoutInflater inflater = getActivity().getLayoutInflater() ;
        builder.setView(inflater.inflate(R.layout.fragment_reset,null)) ;
        builder.setMessage("The mind is an ocean!!")
                .setPositiveButton("Dive in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Change is good!!", Toast.LENGTH_SHORT).show();
                        //verify and facilitate change
                    }
                })
                .setNegativeButton("Too scared!!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Memory is very good!!", Toast.LENGTH_SHORT).show();
                    }
                }) ;
        AlertDialog dialog = builder.create() ;
//        return super.onCreateDialog(savedInstanceState);
        return dialog ;
    }
}
