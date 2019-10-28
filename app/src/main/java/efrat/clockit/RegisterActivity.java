package efrat.clockit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etFirstName,etLastName,etEmail,etPassword;

    //pattern validation for the password:
    private static final Pattern PASSWORD_PATTERN =
           Pattern.compile("^(?=.*\\d)" +  //at least one number
                   "(?=.*[A-Za-z])" +  //at least one letter
                   "(?=\\\\S+$) "+ //no spaces are allowed
                   ".{6}" +        //must contain 6 characters
                   "$") ;       //the end of the string


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister=findViewById(R.id.btnRegister);
        etFirstName=findViewById(R.id.etFirstName);
        etLastName=findViewById(R.id.etLastName);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);

        btnRegister.setOnClickListener(v -> register());






    }

    private void register() {

        if ( ! isEmailValid() || !isPasswordValid())
            return;

        //if both email and password are valid:
        // 1. Logging the user in
        // 2. His details are saved
        //3. He goes to the main activity.

        toggleProgress(true);





    }

    //lets create helper methods: getEmail() , getPassword(), isEmailValid(), isPasswordValid(), toggleProgress(boolean show)

    private String getEmail(){  return etEmail.getText().toString();  }

    private  String getPassword() {return etPassword.getText().toString(); }

    private boolean isEmailValid() {

        if (getEmail().isEmpty()) {
            etEmail.setError("אנא הזן כתובת מייל");
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()) {
            etEmail.setError("כתובת מייל שגויה, אנא הקלד מחדש.");
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }

    private boolean isPasswordValid(){

        if(getPassword().isEmpty()){
            etPassword.setError("אנא הזן סיסמה");
            return false;
        } else if(! PASSWORD_PATTERN.matcher(getPassword()).matches()){
            etPassword.setError("הסיסמא צריכה להיות באורך של 6 תווים ולהכיל מספרים ואותיות");
            return false;
        }else {
            etPassword.setError(null);
            return true;
        }
    }

    ProgressDialog pb;
    private void toggleProgress(boolean show){

        if (pb==null){ //lazy loaded. init it only when we need it.
            pb=new ProgressDialog(this);
            pb.setTitle("Logging you in...");
            pb.setMessage("Please wait");
        }

        if(show)
            pb.show(); //show the dialog window
        else
            pb.dismiss(); //don't show it.
    }






}
