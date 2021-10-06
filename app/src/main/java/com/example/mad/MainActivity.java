package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText place_name;
    EditText place_type;
    EditText owner_name;
    EditText Address;
    EditText txt_email;
    EditText txt_phone1;
    EditText txt_phone2, txt_description;
    DatabaseReference fDatabase;
    Places placeob;



    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        place_name = findViewById(R.id.place_name);
        place_type = findViewById(R.id.place_type);
        owner_name = findViewById(R.id.owner_name);
        Address = findViewById(R.id.Address);
        txt_email = findViewById(R.id.txt_email);
        txt_phone1 = findViewById(R.id.txt_phone1);
        txt_phone2 = findViewById(R.id.txt_phone2);
        txt_description = findViewById(R.id.txt_description);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        placeob = new Places();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, placeview.class);
                startActivity(intent);
            }
        });
    }

    public void Add ( ){
        fDatabase = FirebaseDatabase.getInstance().getReference().child("Places");

        placeob.setPlacename(place_name.getText().toString().trim());
        placeob.setPlacetype(place_type.getText().toString().trim());
        placeob.setOwnername(owner_name.getText().toString().trim());
        placeob.setAddress(Address.getText().toString().trim());
        placeob.setEmail(txt_email.getText().toString().trim());
        placeob.setPhone1(txt_phone1.getText().toString().trim());
        placeob.setPhone2(txt_phone2.getText().toString().trim());
        placeob.setDescription(txt_description.getText().toString().trim());
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(TextUtils.isEmpty(place_name.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter the place name", Toast.LENGTH_LONG).show();
        }

        else if(TextUtils.isEmpty(place_type.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter the place type", Toast.LENGTH_LONG).show();
        }

        else if(TextUtils.isEmpty(owner_name.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter the owner name", Toast.LENGTH_LONG).show();
        }

        else if(TextUtils.isEmpty(Address.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter the Address", Toast.LENGTH_LONG).show();
        }

        else if(TextUtils.isEmpty(txt_email.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter the email", Toast.LENGTH_LONG).show();
        }
        else if (!txt_email.getText().toString().trim().matches(emailPattern)){
            Toast.makeText(getApplicationContext(), "Please enter a valid email address", Toast.LENGTH_LONG).show();
        }

        else if(TextUtils.isEmpty(txt_phone1.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter the mobile", Toast.LENGTH_LONG).show();
        }

        else if(!(txt_phone1.getText().toString().trim().length()==10)) {
            Toast.makeText(getApplicationContext(), "Please enter a valid mobile number contains 10 digits", Toast.LENGTH_LONG).show();
        }

        else if(TextUtils.isEmpty(txt_phone2.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter the office number", Toast.LENGTH_LONG).show();
        }

        else if(!(txt_phone2.getText().toString().trim().length()==10)) {
            Toast.makeText(getApplicationContext(), "Please enter a valid mobile number contains 10 digits", Toast.LENGTH_LONG).show();
        }

        else if(TextUtils.isEmpty(txt_description.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "Please enter the description", Toast.LENGTH_LONG).show();
        }
        else {
            fDatabase.push().setValue(placeob); {
                Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_LONG).show();

            }
        }


}}