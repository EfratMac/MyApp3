package efrat.clockit.recycler;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import efrat.clockit.database.EmployeeDAO;
import efrat.clockit.models.Attendance;

public class AttendanceDataSource {

    public Context context;



    List<Attendance> attendanceReport = new ArrayList<>();

    public AttendanceDataSource(Context context){

        this.context=context;   //we pass the context from the main activity which is the "house" of all fragments.

       attendanceReport = EmployeeDAO.getInstance(context).getAttendanceReport();

        System.out.println(attendanceReport.toString());

//        attendanceReport.add(new Attendance("26/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("27/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("28/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("29/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("26/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("27/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("28/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("29/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("26/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("27/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("28/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("29/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("26/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("27/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("28/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("29/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("26/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("27/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("28/10/2019","08:00","16:00"));
//        attendanceReport.add(new Attendance("29/10/2019","08:00","16:00"));

    }

    public List<Attendance> getAttendanceReport() {
        return attendanceReport;
    }





}

