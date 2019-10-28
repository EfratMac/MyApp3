package efrat.clockit;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import efrat.clockit.database.EmployeeDAO;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabIn, fabOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        findViewById(R.id.fabIn).setOnClickListener((v)-> {

            System.out.println(EmployeeDAO.getInstance(this).getAttendanceObj().toString());


//            new AlertDialog.Builder(this).
//                    //setTitle("דיווח נוכחות").
//                    setMessage("האם לדווח כניסה?").setNegativeButton("לא", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            }).setPositiveButton("כן", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    System.out.println("111111111111");
//
//                }
//            }).show();



            new EntranceCustomDialog().show(getSupportFragmentManager(),"dialog");
//
//
//            if(EmployeeDAO.getInstance(this).add("entrance"))
//              Toast.makeText(this,"כניסה דווחה בשעה "+getCurrentTime(),Toast.LENGTH_SHORT).show();
//          else
//              Toast.makeText(this,"כניסה כבר דווחה",Toast.LENGTH_SHORT).show();

        });


        findViewById(R.id.fabOut).setOnClickListener((v)-> {

            new ExitCustomDialog().show(getSupportFragmentManager(),"dialog");

//            System.out.println(EmployeeDAO.getInstance(this).getAttendanceObj().toString());
//
//            if(EmployeeDAO.getInstance(this).add("exit")) {
//
//                Toast.makeText(this,"יציאה דווחה בשעה "+getCurrentTime(),Toast.LENGTH_SHORT).show();
//                EmployeeDAO.getInstance(this).updateTotal();
//                System.out.println(EmployeeDAO.getInstance(this).getAttendanceObj().toString());
//
//            }
//            else
//                Toast.makeText(this,"יציאה כבר דווחה",Toast.LENGTH_SHORT).show();

        });








    }



    public  Boolean getSSID() {

        String desiredAddress = "e4282564";
        String ssid="";
        Boolean connected = false;

        //WifiManager provides the primary API for managing all aspects of Wi-Fi connectivity.
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //getting information about the connection
        WifiInfo wifiInfo= wifiManager.getConnectionInfo();

       // getSupplicantState()  - Return the detailed state of the supplicant's negotiation with an access point, in the form of a SupplicantState object.
        if (wifiInfo != null){
            ssid = wifiInfo.getSSID();
        }

        connected= desiredAddress.equals(ssid);


        return connected;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private String getCurrentTime(){

        Calendar cal= Calendar.getInstance();
        cal.setTime(new Date());
        String currentTime= new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(cal.getTime());

        return currentTime;
    }
}
