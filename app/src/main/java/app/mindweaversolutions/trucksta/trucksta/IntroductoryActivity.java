package app.mindweaversolutions.trucksta.trucksta;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.mindweaversolutions.trucksta.trucksta.Registration.RegisterActivity;

public class IntroductoryActivity extends AppCompatActivity {

    ActionBar actionBar;
    Button signUpButton;
    Button loginUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        // Binding Views
        actionBar = getSupportActionBar();
        actionBar.hide();
        signUpButton = findViewById(R.id.sign);
        loginUpButton = findViewById(R.id.login);

        // Listener for both the buttons
        loginUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starting login Intent
                startActivity(new Intent(v.getContext(),LoginActivity.class));
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starting Register Intent
                startActivity(new Intent(IntroductoryActivity.this,RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
