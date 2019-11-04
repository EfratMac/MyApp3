package efrat.clockit.controller;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import efrat.clockit.RegisterActivity;
import efrat.clockit.recycler.AttendanceDataSource;
import efrat.clockit.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bnv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        changeFragment(new MainFragment());

   //     bnv.findViewById(R.id.bnv);


        bnv=findViewById(R.id.bnv);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){

                    case R.id.navigation_main:
                        changeFragment(new MainFragment());
                        break;
                    case R.id.navigation_report:
                        changeFragment(new CurrentReportFragment());
                        break;

                    default:
                        return false;
                }

                return true;
            }
        });




     //   new AttendanceDataSource(this);


    //   changeFragment(new CurrentReportFragment());



    }


    @Override
    protected void onStart() {
        super.onStart();


        //a listener to the status of the user authentication
        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuth -> {

            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

            //if there is no user yet, go to the Register Activity first.
            if (user==null){
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
            }
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


    private void changeFragment(Fragment f){

        getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();

    }
}
