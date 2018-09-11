package com.example.shadhin.helloworldonlyjava;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class AfterLogin extends AppCompatActivity {
    TextView name;
    TextView email;
    TextView phone;
    TextView password;
    TextView birthday;
    String sessionId1;
    String sessionId2;
    String sessionId3;
    String sessionId4;
    String sessionId5;
    Button pick_image;
    Button upIntoDbBtn;
    ImageView profile_image;
    DBManager dbManager;
    private static final String IMAGE_DIRECTORY = "/image_store";
    private int GALLERY = 1, CAMERA = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_login);
        name = findViewById(R.id.after_login_name_display);
        email = findViewById(R.id.after_login_email);
        phone = findViewById(R.id.after_login_phone_display);
        birthday = findViewById(R.id.after_login_birthday_display);
        password = findViewById(R.id.after_login_password);
        pick_image=findViewById(R.id.pick_image_btn);
        profile_image=findViewById(R.id.profile_image);
        upIntoDbBtn=findViewById(R.id.up_into_db_btn);
        dbManager = new DBManager(this);
        sessionId1 = getIntent().getStringExtra("nick_name3");
        sessionId2 = getIntent().getStringExtra("phone_number3");
        sessionId3 = getIntent().getStringExtra("birthday3");
        sessionId4 = getIntent().getStringExtra("email3");
        sessionId5 = getIntent().getStringExtra("password3");

        name.setText(sessionId1);
        phone.setText(sessionId2);
        birthday.setText(sessionId3);
        email.setText(sessionId4);
        password.setText(sessionId5);

        upIntoDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_image.setDrawingCacheEnabled(true);
                profile_image.buildDrawingCache();
                Bitmap bitmap = profile_image.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                ContentValues values = new ContentValues();
                values.put(DBManager.COL_ProfilePic,data);

                //dbManager.addToDbImage(data);
                String[] SelectionArgs = {email.getText().toString()};
                int id =  dbManager.update(values, "Email=?", SelectionArgs);
                if (id > 0) {
                    Toast.makeText(getApplicationContext(), "Data is Updated"+data, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data is not Updated"+data, Toast.LENGTH_LONG).show();
                }


            }
        });
        pick_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

    }
    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera",
                "Remove image"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                            case 2:
                                remove_pro_pic();
                        }
                    }
                });
        pictureDialog.show();
    }

    private void remove_pro_pic() {
        profile_image.setImageResource(R.drawable.user);
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(AfterLogin.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    profile_image.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AfterLogin.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            profile_image.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(AfterLogin.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
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
}
