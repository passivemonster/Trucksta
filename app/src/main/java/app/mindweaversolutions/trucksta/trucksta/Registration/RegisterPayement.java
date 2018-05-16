package app.mindweaversolutions.trucksta.trucksta.Registration;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import app.mindweaversolutions.trucksta.trucksta.R;
import app.mindweaversolutions.trucksta.trucksta.model.RegisterUserObject;
import app.mindweaversolutions.trucksta.trucksta.model.RespObj;
import app.mindweaversolutions.trucksta.trucksta.retrofit.ApiUtils;
import app.mindweaversolutions.trucksta.trucksta.retrofit.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPayement extends AppCompatActivity {

    String string;
    Gson gson = new Gson();

    Button next;

    UserService userService;

    EditText accountname,accountifsc,accountnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_payement);

        Bundle extras = getIntent().getExtras();
        string= extras.getString("myjson");
        final RegisterUserObject registerUserObject = gson.fromJson(string, RegisterUserObject.class);

        userService = ApiUtils.getUserService();

        accountifsc = findViewById(R.id.ifsc);
        accountname = findViewById(R.id.accountname);
        accountnumber = findViewById(R.id.accountno);
        next = findViewById(R.id.nextbutton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUserObject.setBname(accountname.getText().toString());
                registerUserObject.setBifsc(accountifsc.getText().toString());
                registerUserObject.setBno(accountnumber.getText().toString());


                Call<RespObj> call = userService.register(registerUserObject.getName(),
                        registerUserObject.getContact(),
                        registerUserObject.getDob(),
                        registerUserObject.getEmail(),
                        registerUserObject.getAddress(),
                        registerUserObject.getVid(),
                        "",
                        registerUserObject.getPid(),"",
                        registerUserObject.getAid(),"",
                        registerUserObject.getDid(),"",
                        registerUserObject.getYears(),
                        "","",registerUserObject.getBname(),
                        registerUserObject.getBno(),
                        registerUserObject.getBifsc());


                call.enqueue(new Callback<RespObj>() {
                    @Override
                    public void onResponse(Call<RespObj> call, Response<RespObj> response) {
                        Toast.makeText(RegisterPayement.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<RespObj> call, Throwable t) {

                        Toast.makeText(RegisterPayement.this, "Network Fail", Toast.LENGTH_SHORT).show();

                    }
                });




                Toast.makeText(RegisterPayement.this, ""+registerUserObject.getAddress(), Toast.LENGTH_SHORT).show();

            }
        });





    }
    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
    }

    protected void onLeaveThisActivity() {
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }



    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent); onStartNewActivity();

    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }

    protected void onStartNewActivity() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

}
