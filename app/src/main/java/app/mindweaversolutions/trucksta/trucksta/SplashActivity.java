package app.mindweaversolutions.trucksta.trucksta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by suryamurugan on 26/3/18.
 */
public class SplashActivity extends AppCompatActivity {
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashActivity.this,LanguageSelectionActivity.class));
        finish();
    }
}