package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        placeob = new Places();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add();
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


        fDatabase.push().setValue(placeob);
                Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_LONG).show();

            }


}