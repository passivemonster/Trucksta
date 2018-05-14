package app.mindweaversolutions.trucksta.trucksta;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.mindweaversolutions.trucksta.trucksta.Registration.RegisterActivity;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private TextView reg;
    private EditText usernameedit;
    private EditText passwordedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameedit =findViewById(R.id.username);
        passwordedit = findViewById(R.id.password);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.reg);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Login Button Listner //
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = usernameedit.getText().toString();
                String password = passwordedit.getText().toString();
                // validate form //
                if(validateLogin(useremail,password)){
                    // doLogin(useremail,password);
                    if (useremail.equals("root") && password.equals("root")){

                       /* Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();*/
                    }

                }
            }
        });
    }



    // VALIDATION for Login //
    private  boolean validateLogin(String useremail, String password){

        if(useremail ==null || useremail.trim().length() ==0 ){
            Toast.makeText(this, "useremail is required", Toast.LENGTH_SHORT).show();
            return false;

        }
        if(password ==null || password.trim().length() ==0 ){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

}
