package efrat.clockit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDB extends SQLiteOpenHelper {

    public static final String DB_NAME="EmployeeDB";
    public static final int DB_VERSION=1;
    public static final String TABLE1_NAME ="DetailsTable";
    public static final String TABLE2_NAME ="MainTable";

    List<String> holidaysdate = Arrays.asList("10/03/2020","09/04/2020","10/04/2020","11/04/2020","12/04/2020",
            "13/04/2020","14/04/2020","15/04/2020","28/04/2020","29/04/2020","29/06/2020","20/09/2020",
            "28/09/2020","03/10/2020","04/10/2020","05/10/2020","06/10/2020","07/10/2020","08/10/2020",
            "09/10/2020","10/10/2020");

    List<String>holidays= Arrays.asList("פורים","פסח","חול המועד","חול המועד","חול המועד",
            "חול המועד","חול המועד","פסח","יום הזיכרון","יום העצמאות","שבועות","ראש השנה",
            "יום כיפור","סוכות","חול המועד","חול המועד","חול המועד","חול המועד","חול המועד",
            "חול המועד","חול המועד");


    private static final String CREATE_DETAILS_TABLE= "CREATE TABLE DetailsTable(FirstName TEXT NOT NULL," +
            "LastName TEXT NOT NULL, Email TEXT NOT NULL, Password TEXT NOT NULL)";

    private static final String CREATE_MAIN_TABLE= "CREATE TABLE MainTable(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "date TEXT,"+
            "day TEXT,"+
            "entrance TEXT , exit TEXT, total TEXT, comments TEXT )";

    //implementing ctor
    public EmployeeDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //That happens ONCE. creating the tables.
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_DETAILS_TABLE);
        db.execSQL(CREATE_MAIN_TABLE);

        assert getDates().size()==getDaysOfWeek().size();

        for (int i = 0; i <434 ; i++) {
            db.insert(TABLE2_NAME,null ,contentValues(i));
        }

        for (int i = 0; i < holidaysdate.size(); i++) {
            db.execSQL(getString(holidays.get(i),holidaysdate.get(i)));
        }

    }


    //that happens when we need to update the database structure.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //destroy the tables
        db.execSQL("DROP TABLE DetailsTable");
        db.execSQL("DROP TABLE MainTable");

        //create new tables
        onCreate(db);
    }

    // **************  HELPER METHODS: ****************************** //

        private String getString(String holiday, String date) {

        String strSQL = "UPDATE " +TABLE2_NAME+" SET comments = '"+holiday+"'"+ "WHERE date='"+date+"'";

        return strSQL;
    }

    private List<String> getDates(){

        String startDate="24/10/2019";
        List<String> dates = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c= Calendar.getInstance();
        try {
            c.setTime(sdf.parse(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dates.add(startDate);
        for (int i = 1; i < 434; i++) {
            c.add(Calendar.DAY_OF_MONTH,1); //adding 1 to the date
            String newDay = sdf.format(c.getTime()); //saving it in newDay
            dates.add(newDay);
        }

        return dates;
    }

    private List<String> getDaysOfWeek(){

        String startDate="24/10/2019";
        List<String> days = new ArrayList<>();
        String [] dayOfWeek = {"א","ב","ג","ד","ה","ו","ש"};

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c= Calendar.getInstance();
        try {
            c.setTime(sdf.parse(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        days.add(dayOfWeek[c.get(Calendar.DAY_OF_WEEK)-1]);
        for (int i = 1; i < 434; i++) {
            c.add(Calendar.DAY_OF_MONTH,1); //adding 1 to the date
            days.add(dayOfWeek[c.get(Calendar.DAY_OF_WEEK)-1]);
        }

        return days;

    }

    public ContentValues contentValues(int i){

        ContentValues cv=new ContentValues();
        cv.put("date", getDates().get(i));
        cv.put("day", getDaysOfWeek().get(i));

        return cv;

    }



}
