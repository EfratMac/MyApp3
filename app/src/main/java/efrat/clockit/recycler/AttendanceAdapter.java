package efrat.clockit.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import efrat.clockit.models.Attendance;
import efrat.clockit.recycler.AttendanceViewHolder;

import efrat.clockit.R;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceViewHolder> {

    List<Attendance>data;

    //the ctor AttendanceAdaper gets the list
    public AttendanceAdapter(List<Attendance> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Each view holds context. we get the context from the parent.
        Context ctx  = parent.getContext();

        //we want to obtain a reference to the Layout inflater:
        LayoutInflater inflater=LayoutInflater.from(ctx);

        //we create the view
        View v= inflater.inflate(R.layout.attendance_item,parent,false);

        return new AttendanceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {

        //we get attendance obj

        Attendance a=data.get(position);

        holder.tvDate.setText(a.getDate());
        holder.tvIn.setText(a.getIn());
        holder.tvOut.setText(a.getOut());
        holder.tvTotal.setText(a.getTotal());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
