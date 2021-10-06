package com.example.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import io.grpc.Context;

public class mainAdapter extends RecyclerView.Adapter<mainAdapter.myviewHolder> {

    placeview context;

    ArrayList<Places> list;

    public mainAdapter(placeview context, ArrayList<Places> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.main_item,parent, false);

        return new myviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {
        Places places = list.get(position);
        holder.placename.setText("Name of the Place : "+ places.getPlacename());
        holder.placetype.setText("Place Type : "+ places.getPlacetype());
        holder.ownername.setText("Owner name : "+ places.getOwnername());
        holder.Address.setText("Address : "+places.getAddress());
        holder.email.setText("Email : "+places.getEmail());
        holder.phone1.setText("Mobile : "+places.getPhone1());
        holder.phone2.setText("Office : "+places.getPhone2());
        holder.description.setText("Description : "+places.getDescription());

    }

    @Override
    public int getItemCount() { return list.size();}

    class myviewHolder extends RecyclerView.ViewHolder{
        TextView placename,placetype,ownername,Address,email,phone1,phone2,description;

        public myviewHolder(@NonNull View itemview){
            super(itemview);

            placename=(TextView)itemview.findViewById(R.id.place_name);
            placetype=(TextView)itemview.findViewById(R.id.place_type);
            ownername=(TextView)itemview.findViewById(R.id.owner_name);
            Address=(TextView)itemview.findViewById(R.id.Address);
            email=(TextView)itemview.findViewById(R.id.email);
            phone1=(TextView)itemview.findViewById(R.id.txt_phone1);
            phone2=(TextView)itemview.findViewById(R.id.txt_phone2);
            description=(TextView)itemview.findViewById(R.id.txt_description);

        }

    }
}
