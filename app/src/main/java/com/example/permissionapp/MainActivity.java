package com.example.permissionapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int PERMISSION_CODE_REQUEST = 200;
    private Switch swStorage;
    private Switch swLocation;
    private Switch swCamera;
    private Switch swPhone;
    private Switch swContacts;
    ArrayList<Switch> switches = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        switchCheck();
        System.out.println("oka");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swStorage = findViewById(R.id.swStorage);
        swLocation = findViewById(R.id.swLocation);
        swCamera = findViewById(R.id.swCamera);
        swPhone = findViewById(R.id.swPhone);
        swContacts = findViewById(R.id.swContacts);

        Button btnContinue = findViewById(R.id.btnContinue);

        switches.add(swStorage);
        switches.add(swLocation);
        switches.add(swCamera);
        switches.add(swPhone);
        switches.add(swContacts);


//        for (Switch value: switches) {
//
//            value.setOnCheckedChangeListener((buttonView, isChecked) -> {
//                if (ContextCompat.checkSelfPermission(MainActivity.this,
//                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//                    swStorage.setChecked(true);
//                }
//                if(isChecked){
//
//                }
//            });
//        }

        btnContinue.setOnClickListener(v -> {
            ArrayList<String> permissions = new ArrayList<>();

            if(!(ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && swStorage.isChecked()){

                    permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            if(!(ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) && swLocation.isChecked()){
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }

            if(!(ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) && swCamera.isChecked()){
                permissions.add(Manifest.permission.CAMERA);
            }

            if(!(ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) && swPhone.isChecked()){
                permissions.add(Manifest.permission.CALL_PHONE);
            }

            if(!(ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) && swContacts.isChecked()){
                permissions.add(Manifest.permission.READ_CONTACTS);
            }

            if(permissions.size() > 0){
                requestStoragePermission(permissions);
            }else{
                Intent intent = new Intent(this, ActionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void requestStoragePermission(ArrayList<String> permissions) {
        String[] perms = new String[permissions.size()];
        perms = permissions.toArray(perms);

        ActivityCompat.requestPermissions(this,  perms, PERMISSION_CODE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Intent intent = new Intent(this, ActionActivity.class);
        startActivity(intent);

        if(requestCode == PERMISSION_CODE_REQUEST){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();

            }
        }



//        switch (requestCode) {
//            case 200:
//                boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                break;
//        }
    }

    public void switchCheck(){
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            swStorage.setChecked(true);
            swStorage.setOnCheckedChangeListener((buttonView, isChecked) -> {
                swStorage.setChecked(true);
            });
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            swLocation.setChecked(true);
            swLocation.setOnCheckedChangeListener((buttonView, isChecked) -> {
                swLocation.setChecked(true);
            });
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            swCamera.setChecked(true);
            swCamera.setOnCheckedChangeListener((buttonView, isChecked) -> {
                swCamera.setChecked(true);
            });
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            swPhone.setChecked(true);
            swPhone.setOnCheckedChangeListener((buttonView, isChecked) -> {
                swPhone.setChecked(true);
            });
        }


        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            swContacts.setChecked(true);
            swContacts.setOnCheckedChangeListener((buttonView, isChecked) -> {
                swContacts.setChecked(true);
            });
        }
    }

//    public void jump(View view){
//        Intent intent = new Intent(this, ActionActivity.class);
//        startActivity(intent);
//    }

    public void exit(View view){
        finish();
    }
}