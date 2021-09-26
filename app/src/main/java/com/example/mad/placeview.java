package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class placeview extends AppCompatActivity {

    RecyclerView recyclerView;
    mainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeview);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Places> options =
                new FirebaseRecyclerOptions.Builder<Places>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Places"), Places.class)
                        .build();

        mainAdapter = new mainAdapter(options);
        recyclerView.setAdapter(mainAdapter);
    }
}