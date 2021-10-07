package com.example.travelappsl.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.travelappsl.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.travelappsl.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.travelappsl.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity {

    RecyclerView featuredRecycler;
    FeaturedAdapter featuredAdapter;
    //RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        featuredRecycler = (RecyclerView) findViewById(R.id.featured_recycler);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<FeaturedHelperClass> options =
                new FirebaseRecyclerOptions.Builder<FeaturedHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Places"), FeaturedHelperClass.class)
                        .build();

        featuredAdapter = new FeaturedAdapter(options);
        featuredRecycler.setAdapter(featuredAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        featuredAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        featuredAdapter.stopListening();
    }
    //    private void featuredRecycler() {
//
//        featuredRecycler.setHasFixedSize(true);
//        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
//
//        ArrayList<FeaturedHelperClass> featuredLocation = new ArrayList<>();
//
//        featuredLocation.add(new FeaturedHelperClass("Sigiriya","Bla bla bla blab lab la", R.drawable.ic_launcher_foreground));
//        featuredLocation.add(new FeaturedHelperClass("Yala","Bla bla bla blab lab la", R.drawable.ic_launcher_foreground));
//        featuredLocation.add(new FeaturedHelperClass("SLIIT","Bla bla bla blab lab la", R.drawable.hotel_icon));
//
//        adapter = new FeaturedAdapter(featuredLocation);
//        featuredRecycler.setAdapter(adapter);
//    }
}