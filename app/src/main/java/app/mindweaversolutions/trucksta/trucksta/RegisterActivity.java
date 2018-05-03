package app.mindweaversolutions.trucksta.trucksta;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // Hiding ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();
    }
}
