package efrat.clockit.controller;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import efrat.clockit.Other;
import efrat.clockit.R;
import efrat.clockit.controller.EntranceCustomDialog;
import efrat.clockit.controller.ExitCustomDialog;
import efrat.clockit.database.EmployeeDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fabIn= view.findViewById(R.id.fabIn);
        FloatingActionButton fabOut= view.findViewById(R.id.fabOut);
        TextView tvMsg=view.findViewById(R.id.tvMsg);

        int hour = Integer.parseInt(Other.getCurrent("HH:mm:ss").substring(0, 2));

        if(hour>5 && hour<12)
            tvMsg.setText("בוקר טוב");
        else if(hour<16)
            tvMsg.setText("צהריים טובים");
        else if(hour<18)
            tvMsg.setText("אחר הצהרים טובים");
        else if (hour<20)
            tvMsg.setText("ערב טוב");
        else
            tvMsg.setText("לילה טוב");




        fabIn.setOnClickListener(v->{

            System.out.println(EmployeeDAO.getInstance(getContext()).getAttendanceObj().toString());

            new EntranceCustomDialog().show(getFragmentManager(),"dialog");

        });


        fabOut.setOnClickListener(v->{

            new ExitCustomDialog().show(getFragmentManager(),"dialog");

        });








    }
}
