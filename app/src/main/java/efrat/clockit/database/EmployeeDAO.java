
package efrat.clockit.database;

//This class is for database actions: CRUD- Create, Read, Update, Delete.

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.SplittableRandom;

import efrat.clockit.models.Attendance;

public class EmployeeDAO {

    //property - the database.
    private SQLiteDatabase db;

    //the EmployeeDAO gets the database and saves it so it will be able to do actions on it.
    //the EmployeeDAO is singleton- it has a private ctor so it wont be able to create instances of this class.
    //there is ONLY ONE INSTANCE (single).
    private EmployeeDAO(SQLiteDatabase db){
        this.db=db;
    }

    private static EmployeeDAO instance; //creating the single instance. it is of type of the class.
    //static - one shared object.

    //init the instance. lazy loaded. if the instance is null, it init it and returns it.
    //if it is not null, it just returns it.
    public synchronized static EmployeeDAO getInstance(Context c){

        if(instance == null){
            EmployeeDB edb=new EmployeeDB(c); //creating a mofa of the database.
            SQLiteDatabase db = edb.getWritableDatabase();
            instance= new EmployeeDAO(db); //the instance is of type EmployeeDAO.

        }

        return instance;
    }



    public void add2(String s){

        Calendar cal= Calendar.getInstance();
        cal.setTime(new Date());



        String strSQL = "UPDATE " +EmployeeDB.TABLE2_NAME+" SET "+s+" = '"+getCurrent("HH:mm:ss")+"'"+
                "WHERE date='"+getCurrent("dd/MM/yyyy")+"'";

        db.execSQL(strSQL);

    }



    public boolean addEtn(String s){

        Calendar cal= Calendar.getInstance();
        cal.setTime(new Date());

        if (getAttendanceObj().getIn()== null){

            String strSQL = "UPDATE " +EmployeeDB.TABLE2_NAME+" SET "+s+" = '"+getCurrent("HH:mm:ss")+"'"+
                    "WHERE date='"+getCurrent("dd/MM/yyyy")+"'";

            db.execSQL(strSQL);

          return true;

        } else {

            return false;
        }

    }


    public boolean addExit(String s){

        Calendar cal= Calendar.getInstance();
        cal.setTime(new Date());

        if (getAttendanceObj().getOut()== null){

            String strSQL = "UPDATE " +EmployeeDB.TABLE2_NAME+" SET "+s+" = '"+getCurrent("HH:mm:ss")+"'"+
                    "WHERE date='"+getCurrent("dd/MM/yyyy")+"'";

            db.execSQL(strSQL);

            return true;

        } else {

            return false;
        }

    }


    public boolean add(String s){

        Calendar cal= Calendar.getInstance();
        cal.setTime(new Date());

        if(s=="entrance") {

            if (getAttendanceObj().getIn()== null){

                String strSQL = "UPDATE " +EmployeeDB.TABLE2_NAME+" SET "+s+" = '"+getCurrent("HH:mm:ss")+"'"+
                        "WHERE date='"+getCurrent("dd/MM/yyyy")+"'";

                db.execSQL(strSQL);

                return true;

            } else {

                return false;
            }


        } else  {

            if (getAttendanceObj().getOut() == null) {

                String strSQL = "UPDATE " + EmployeeDB.TABLE2_NAME + " SET " + s + " = '" + getCurrent("HH:mm:ss") + "'" +
                        "WHERE date='" + getCurrent("dd/MM/yyyy") + "'";

                db.execSQL(strSQL);

                return true;

            } else {

                return false;
            }

        }

    }

    //READ - get the whole report.
    public List<Attendance> getAttendanceReport(){

        List<Attendance> report = new ArrayList<>();

        //choose the columns we want:
        String [] projection={"date","entrance","exit"};

        //TODO: HERE WE CAN CHOOSE WHICH DATES TO SEE AND THEREFORE TO CHOOSE A SPECIFIC MONTH.
        String selection= "date like ?";
        String [] selectionArgs={"%/11/2019"}; //this will give all the days in february (of all years).

        Cursor cursor = db.query(EmployeeDB.TABLE2_NAME, projection, selection, selectionArgs, null, null, null);

        if( !cursor.moveToFirst()) //if the table is empty.
            return report;        //we have to return a list so in this case it will return an empty list.

        //else - go through all the table and fill the list.

        do{

            //int id=cursor.getInt(0);
            String date=cursor.getString(cursor.getColumnIndex("date"));
            String entrance=cursor.getString(cursor.getColumnIndex("entrance"));
            String exit=cursor.getString(cursor.getColumnIndex("exit"));

            report.add(new Attendance(date,entrance,exit));

        } while (cursor.moveToNext());

        cursor.close(); //tp free the cursor.

        return report;

    }


    //READ - get a certain data.
    public Attendance getAttendanceObj(){

        String [] projection={"date","entrance","exit"};
        String selection= "date LIKE ?";
        // String [] selectionArgs={getCurrent("dd/MM/yyyy")};
        String [] selectionArgs={getCurrent("dd/MM/yyyy")};

        String date;
        String in;
        String out;

        Cursor cursor = db.query(EmployeeDB.TABLE2_NAME, projection, selection, selectionArgs, null, null, null);

       cursor.moveToFirst();


            do {

                 date = cursor.getString(cursor.getColumnIndex("date"));
                 in = cursor.getString(cursor.getColumnIndex("entrance"));
                 out = cursor.getString(cursor.getColumnIndex("exit"));

            } while (cursor.moveToNext());

        cursor.close();

        return new Attendance(date,in,out);

    }

    public void updateTotal(){

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        String date= getAttendanceObj().getDate();
        String in = getAttendanceObj().getIn();
        String out = getAttendanceObj().getOut();

        if (in!=null && out!= null) {
            try {
                Date t1 = formatter.parse(in); //gets the milliseconds since 1970 00:00
                Date t2 = formatter.parse(out);  //gets the milliseconds since 1970 00:00
                long diff = t2.getTime() - t1.getTime();
                long diffSeconds = diff / 1000;
                long diffMin = diffSeconds / 60;

                long hours = diffMin / 60;
                long min = diffMin % 60;

                String total = hours + "." + min;

//                String strSQL = "UPDATE " + EmployeeDB.TABLE2_NAME + " SET total='" + total + "'" +
//                        " WHERE date='" + getCurrent("dd/MM/yyyy") + "' AND entrance IS NOT NULL AND exit IS NOT NULL";

                String strSQL1 = "UPDATE " + EmployeeDB.TABLE2_NAME + " SET total='" + total + "'" +
                        " WHERE date='" + date+"'";


                String strSQL = "UPDATE " + EmployeeDB.TABLE2_NAME + " SET total=1 WHERE date= '27/10/2019'";

                db.execSQL(strSQL1);

            } catch (ParseException e) {
                System.out.println("problem parsing");
            }

        }

    }





    //HELPER METHOD

    public String getCurrent(String pattern){
        Calendar cal= Calendar.getInstance();
        cal.setTime(new Date());
        String current= new SimpleDateFormat(pattern, Locale.getDefault()).format(cal.getTime());

        return current;

    }





}

