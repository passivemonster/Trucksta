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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);
        actionBar = getSupportActionBar();
        actionBar.hide();

        Button signUpButton = findViewById(R.id.sign);
        Button loginUpButton=findViewById(R.id.login);

        loginUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(v.getContext(),LoginActivity.class);
                startActivity(intent2);

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroductoryActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

}
