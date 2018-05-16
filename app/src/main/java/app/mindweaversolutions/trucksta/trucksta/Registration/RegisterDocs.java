package app.mindweaversolutions.trucksta.trucksta.Registration;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import app.mindweaversolutions.trucksta.trucksta.R;
import app.mindweaversolutions.trucksta.trucksta.model.RegisterUserObject;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class RegisterDocs extends AppCompatActivity  {

    String string;
    Gson gson = new Gson();

    EditText voteridno,pancardno,aadharno,dlno;


    ImageButton voterb,panb,aadharb,dlb;
    int VOTER_REQUEST=123,PAN_REQUEST=4566,AADHAR_REQUEST=56453,DL_REQUEST=46456;
    private int GALLERY = 1, CAMERA = 2;
    private static final String IMAGE_DIRECTORY = "/mws";

    private void showPictureDialog(final int from){

        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary(from);
                                break;
                            case 1:
                                takePhotoFromCamera(from);
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary(int from) {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY+from);

    }

    private void takePhotoFromCamera(int from) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA+from);
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_docs);

        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();



        Bundle extras = getIntent().getExtras();
        string= extras.getString("myjson");
        final RegisterUserObject registerUserObject = gson.fromJson(string, RegisterUserObject.class);


        voteridno = findViewById(R.id.voterno);
        pancardno = findViewById(R.id.panno);
        aadharno = findViewById(R.id.aadharno);
        dlno = findViewById(R.id.dlno);



        final Button nexttopersonal = findViewById(R.id.nexttopersonal);

        voterb = findViewById(R.id.voterb);
        panb = findViewById(R.id.panb);
        aadharb = findViewById(R.id.aadharb);
        dlb = findViewById(R.id.dlb);


        voterb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*selectImage(VOTER_REQUEST);*/

                showPictureDialog(VOTER_REQUEST);

            }
        });

        panb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog(PAN_REQUEST);
            }
        });

        aadharb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog(AADHAR_REQUEST);
            }
        });

        dlb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showPictureDialog(DL_REQUEST);
            }
        });

        nexttopersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUserObject.setPid(pancardno.getText().toString());
                registerUserObject.setAid(aadharno.getText().toString());
                registerUserObject.setDid(dlno.getText().toString());
                registerUserObject.setVid(voteridno.getText().toString());


                Gson gson1 = new Gson();
                String myJson1 = gson1.toJson(registerUserObject);

                Intent intent= new Intent(RegisterDocs.this,RegisterPersonal.class);
                intent.putExtra("myjson", myJson1);
                startActivity(intent);

            }
        });
    }

    // This is for Image Upload //
    public void selectImage(int requestid) {


        Toast.makeText(RegisterDocs.this, "select Imaage called", Toast.LENGTH_SHORT).show();

        // Intent to pick and image for upload
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, requestid);

      /*  CropImage.activity()
                .start(getContext(), this);*/
    }
    ///////////////////////////////


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
/////////////////////////////////////////////////////
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }




        if (requestCode == GALLERY+VOTER_REQUEST) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(RegisterDocs.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    voterb.setImageBitmap(bitmap);
                    voterb.setScaleType(ImageView.ScaleType.CENTER);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterDocs.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA+VOTER_REQUEST) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            voterb.setImageBitmap(thumbnail);

            saveImage(thumbnail);
            Toast.makeText(RegisterDocs.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        } else if (requestCode == GALLERY+PAN_REQUEST) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(RegisterDocs.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    panb.setImageBitmap(bitmap);
                    panb.setScaleType(ImageView.ScaleType.CENTER);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterDocs.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA+PAN_REQUEST) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            panb.setImageBitmap(thumbnail);

            saveImage(thumbnail);
            Toast.makeText(RegisterDocs.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        } else if (requestCode == GALLERY+AADHAR_REQUEST) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(RegisterDocs.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    aadharb.setImageBitmap(bitmap);
                    aadharb.setScaleType(ImageView.ScaleType.CENTER);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterDocs.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA+AADHAR_REQUEST) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            aadharb.setImageBitmap(thumbnail);

            saveImage(thumbnail);
            Toast.makeText(RegisterDocs.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        } else if (requestCode == GALLERY+DL_REQUEST) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(RegisterDocs.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    dlb.setImageBitmap(bitmap);
                    dlb.setScaleType(ImageView.ScaleType.CENTER);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterDocs.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA+DL_REQUEST) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            dlb.setImageBitmap(thumbnail);

            saveImage(thumbnail);
            Toast.makeText(RegisterDocs.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }


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
