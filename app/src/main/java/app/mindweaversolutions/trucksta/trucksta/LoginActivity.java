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

import java.util.List;

import app.mindweaversolutions.trucksta.trucksta.Registration.RegisterActivity;
import app.mindweaversolutions.trucksta.trucksta.model.LoginResp;
import app.mindweaversolutions.trucksta.trucksta.model.RespObj;
import app.mindweaversolutions.trucksta.trucksta.retrofit.ApiUtils;
import app.mindweaversolutions.trucksta.trucksta.retrofit.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private TextView reg;
    private EditText usernameedit;
    private EditText passwordedit;

    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameedit =findViewById(R.id.username);
        passwordedit = findViewById(R.id.password);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.reg);

        userService = ApiUtils.getUserService();



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
                   /////////



                    Call<LoginResp> call = userService.login(useremail,password);
                    call.enqueue(new Callback<LoginResp>() {
                        @Override
                        public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {
                            Toast.makeText(LoginActivity.this, ""+response.body().getName()
                                    +++++++++++++, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<LoginResp> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "GET LUNCH  !! ", Toast.LENGTH_SHORT).show();
                        }
                    });

                    //////////////



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
