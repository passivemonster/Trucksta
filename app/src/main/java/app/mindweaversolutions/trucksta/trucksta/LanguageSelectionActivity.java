package app.mindweaversolutions.trucksta.trucksta;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import app.mindweaversolutions.trucksta.trucksta.db.AppDatabase;
import app.mindweaversolutions.trucksta.trucksta.db.locationData;

public class LanguageSelectionActivity extends AppCompatActivity {
    TextView locationdata;
    ActionBar actionbar;
    Button english;
    LocationManager locationManager;
    Location lastKnownLocation;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
        actionbar = getSupportActionBar();
        actionbar.hide();
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();
        Toast.makeText(this, ""+getMacAddr(), Toast.LENGTH_SHORT).show();
        final locationData locatiodata = new locationData();
        locatiodata.setLatitude("324234");
        locatiodata.setLongitude("dfdsfsdfsd");
        List<locationData> locationData = AppDatabase.getAppDatabase(getApplicationContext()).locationDao().getAll();
        for (locationData locatiodata1 : locationData){
          //  Toast.makeText(this, "\n"+locatiodata1.getUid(), Toast.LENGTH_SHORT).show();
        }





        // Binding Views
        english = findViewById(R.id.english);
        locationdata = findViewById(R.id.locationdata);

        // Checking location permission
        checkLocationPermission();

        // Listener for selecting a language
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en-rIN");
            }
        });

        // Acquire a reference to the system Location Manager
       locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider2 = LocationManager.NETWORK_PROVIDER;
        // Or use LocationManager.GPS_PROVIDER
         lastKnownLocation = locationManager.getLastKnownLocation(locationProvider2);

        locationdata.setText(""+lastKnownLocation.getLatitude()+lastKnownLocation.getLongitude());

        // This is a LocationListener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            @SuppressLint("NewApi")
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.

                Log.e("locationservice",location.getLatitude()+"\n"+location.getLongitude()+""+location.getBearingAccuracyDegrees());
                Log.e("locationservice",""+isBetterLocation(location,lastKnownLocation));
                locationdata.setText(""+location.getLongitude()+"\n"+location.getLatitude());
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            public void onProviderEnabled(String provider) {
                Toast.makeText(LanguageSelectionActivity.this, ""+provider, Toast.LENGTH_SHORT).show();
            }
            public void onProviderDisabled(String provider) {

            }
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }



    public void setLocale(String lang){
        Locale myLocale=new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        Intent intro=new Intent(LanguageSelectionActivity.this,IntroductoryActivity.class);
        startActivity(intro);
        finish();
    }


    // Checking permission Method - Can be called to check location permission
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Title")
                        .setMessage("Location Permission Content")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(LanguageSelectionActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    // Permission Request Method
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    checkLocationPermission();
                }
                return;
            }

        }
    }
    private static final int TWO_MINUTES = 1000 * 60 * 2;

    /** Determines whether one Location reading is better than the current Location fix
     * @param location  The new Location that you want to evaluate
     * @param currentBestLocation  The current Location fix, to which you want to compare the new one
     */
    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }
        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;
        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }
        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;
        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());
        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }
    /** Checks whether two providers are the same */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }
}
