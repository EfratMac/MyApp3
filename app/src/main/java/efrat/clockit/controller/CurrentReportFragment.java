package efrat.clockit.controller;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import efrat.clockit.R;
import efrat.clockit.recycler.AttendanceAdapter;
import efrat.clockit.recycler.AttendanceDataSource;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentReportFragment extends Fragment {


    public CurrentReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_report, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvReport= view.findViewById(R.id.rvReport);

        MainActivity a = (MainActivity) getActivity();

        if(a==null) return;

        //lets use the adapter in order to display the data:


        AttendanceAdapter adapter= new AttendanceAdapter(new AttendanceDataSource(a).getAttendanceReport());

        rvReport.setAdapter(adapter);

        rvReport.setLayoutManager(new LinearLayoutManager(view.getContext()));


    }


}
