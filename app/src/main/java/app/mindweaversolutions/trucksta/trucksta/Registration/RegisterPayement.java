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

public class RegisterPayement extends AppCompatActivity {

    String string;
    Gson gson = new Gson();

    Button next;

    EditText accountname,accountifsc,accountnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_payement);

        Bundle extras = getIntent().getExtras();
        string= extras.getString("myjson");
        final RegisterUserObject registerUserObject = gson.fromJson(string, RegisterUserObject.class);

        accountifsc = findViewById(R.id.ifsc);
        accountname = findViewById(R.id.accountname);
        accountnumber = findViewById(R.id.aadharno);
        next = findViewById(R.id.nextbutton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUserObject.setBname(accountname.getText().toString());
                registerUserObject.setBifsc(accountifsc.getText().toString());
                registerUserObject.setBno(accountnumber.getText().toString());


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
