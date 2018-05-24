package app.mindweaversolutions.trucksta.trucksta.Registration;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import app.mindweaversolutions.trucksta.trucksta.R;
import app.mindweaversolutions.trucksta.trucksta.model.RegisterUserObject;

public class RegisterPersonal extends AppCompatActivity {


    String string;
    Gson gson = new Gson();

    EditText years;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_personal);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide() ;

        Bundle extras = getIntent().getExtras();
        string= extras.getString("myjson");
        final RegisterUserObject registerUserObject = gson.fromJson(string, RegisterUserObject.class);

years = findViewById(R.id.years);


        Button next = findViewById(R.id.nextbutton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUserObject.setYears(years.getText().toString());

                Gson gson1 = new Gson();
                String myJson1 = gson1.toJson(registerUserObject);
                Intent intent= new Intent(RegisterPersonal.this,RegisterPayement.class);
                intent.putExtra("myjson", myJson1);
                startActivity(intent);

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
