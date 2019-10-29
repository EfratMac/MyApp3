package efrat.clockit.controller;


import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import efrat.clockit.Other;
import efrat.clockit.R;
import efrat.clockit.database.EmployeeDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class EntranceCustomDialog extends DialogFragment {

    View v;

    public EntranceCustomDialog() {  //NOT ALLOWED TO CREATE ANOTHER CONSTRUCTOR !
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_entrance_custom_dialog, container, false);


        Button btnYes=view.findViewById(R.id.btnYes);
        Button btnNo= view.findViewById(R.id.btnNo);

        btnNo.setOnClickListener(v->{

            dismiss();

        });

        btnYes.setOnClickListener(v->{

            if (EmployeeDAO.getInstance(view.getContext()).add("entrance")) {
                Toast.makeText(view.getContext(), "כניסה דווחה בשעה " + Other.getCurrent("HH:mm:ss"), Toast.LENGTH_SHORT).show();
                dismiss();

            } else
                Toast.makeText(view.getContext(), "כניסה כבר דווחה", Toast.LENGTH_SHORT).show();
            dismiss();


        });

        //we need to wait until the view is loaded and only then set its width and height.
        //so we add a new Listener to the layout status.

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                ViewGroup.LayoutParams params=view.getLayoutParams();

                //the width and the height of the screen:

                int width= getResources().getDisplayMetrics().widthPixels;
                int height=getResources().getDisplayMetrics().heightPixels;

                params.width=(int) (width*0.6);
                params.height=(int) (height*0.2);


            }
        });



        return view;
    }

}
