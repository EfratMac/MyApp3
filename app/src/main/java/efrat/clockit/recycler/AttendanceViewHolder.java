package efrat.clockit.recycler;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import efrat.clockit.R;


public class AttendanceViewHolder extends RecyclerView.ViewHolder {

    TextView tvDate;
    TextView tvIn;
    TextView tvOut;
    TextView tvTotal;
    ImageView imDay;



    public AttendanceViewHolder(@NonNull View itemView) {
        super(itemView);

        tvDate=itemView.findViewById(R.id.tvDate);
        tvIn=itemView.findViewById(R.id.tvIn);
        tvOut=itemView.findViewById(R.id.tvOut);
        tvTotal=itemView.findViewById(R.id.tvTotal);
        imDay=itemView.findViewById(R.id.imDay);


    }
}
