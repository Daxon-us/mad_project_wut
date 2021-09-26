package com.example.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class mainAdapter extends FirebaseRecyclerAdapter<Places,mainAdapter.myviewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public mainAdapter(@NonNull FirebaseRecyclerOptions<Places> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull Places model) {
        holder.placename.setText(model.getPlacename());
        holder.placetype.setText(model.getPlacetype());
        holder.ownername.setText(model.getOwnername());
        holder.Address.setText(model.getAddress());
        holder.email.setText(model.getEmail());
        holder.phone1.setText(model.getPhone1());
        holder.phone2.setText(model.getPhone2());
        holder.description.setText(model.getDescription());

    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_placeview,parent,false);
        return new myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder{
        TextView placename,placetype,ownername,Address,email,phone1,phone2,description;

        public myviewHolder(@NonNull View itemview){
            super(itemview);

            placename=(TextView)itemview.findViewById(R.id.place_name);
            placetype=(TextView)itemview.findViewById(R.id.place_type);
            ownername=(TextView)itemview.findViewById(R.id.owner_name);
            Address=(TextView)itemview.findViewById(R.id.Address);
            email=(TextView)itemview.findViewById(R.id.txt_email);
            phone1=(TextView)itemview.findViewById(R.id.txt_phone1);
            phone2=(TextView)itemview.findViewById(R.id.txt_phone2);
            description=(TextView)itemview.findViewById(R.id.txt_description);

        }

    }
}
