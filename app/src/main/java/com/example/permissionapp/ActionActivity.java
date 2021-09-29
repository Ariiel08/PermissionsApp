package com.example.permissionapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;

public class ActionActivity extends AppCompatActivity {
    Button btnStorage;
    Button btnLocation;
    Button btnCamera;
    Button btnPhone;
    Button btnContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        btnStorage = findViewById(R.id.btnStorage);
        btnLocation = findViewById(R.id.btnLocation);
        btnCamera = findViewById(R.id.btnCamera);
        btnPhone = findViewById(R.id.btnPhone);
        btnContacts = findViewById(R.id.btnContacts);

        btnStorage.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(ActionActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse("/");
                intent.setDataAndType(uri, "*/*");
                startActivity(Intent.createChooser(intent, "Open folder"));
            }else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        });

        btnLocation.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(ActionActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(ActionActivity.this,
                        Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCamera.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(ActionActivity.this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
            }else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        });

        btnPhone.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(ActionActivity.this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8293718958"));
                startActivity(intent);
            }else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        });

        btnContacts.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(ActionActivity.this,
                    Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        });

    }
}