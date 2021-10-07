package com.example.travelappsl.HelperClasses.HomeAdapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelappsl.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FeaturedAdapter extends FirebaseRecyclerAdapter<FeaturedHelperClass,FeaturedAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FeaturedAdapter(@NonNull FirebaseRecyclerOptions<FeaturedHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull FeaturedHelperClass model) {
        holder.placename.setText(model.getPlacename());
        holder.description.setText(model.getDescription());

        Glide.with(holder.img.getContext())
                .load(model.getIurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        //view more button
//        holder.btnViewMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final DialogPlus dialogPlus = DialogPlus.newDialog(view.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.view_more_popup))
//                        .setExpanded(true,700)
//                        .create();
//
//                dialogPlus.show();
//
//            }
//        });


        //Update
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();

                dialogPlus.show();

                View vieww = dialogPlus.getHolderView();

                EditText name = vieww.findViewById(R.id.txt_name);
                EditText email = vieww.findViewById(R.id.txt_email);
                EditText phone = vieww.findViewById(R.id.txt_phone1);
                EditText iurl = vieww.findViewById(R.id.txt_iurl);

                Button btnUpdate = vieww.findViewById(R.id.btnUpdate);

                name.setText(model.getPlacename());
                email.setText(model.getEmail());
                phone.setText(model.getPhone1());
                iurl.setText(model.getIurl());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("placename",name.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("phone1",phone.getText().toString());
                        map.put("iurl",iurl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Places")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.placename.getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.placename.getContext(), "Error!", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        //Delete
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.placename.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be Recovered.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Places")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.placename.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView placename,description, email, phone1, address;

        Button btnViewMore, btnEdit, btnDelete, btnUpdate;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.img1);
            placename=(TextView)itemView.findViewById(R.id.txt_placename);
            description=(TextView)itemView.findViewById(R.id.txt_description);
            email=(TextView)itemView.findViewById(R.id.txt_email);
            phone1=(TextView)itemView.findViewById(R.id.txt_phone1);
            //address=(TextView)itemView.findViewById(R.id.txt_address);

            //btnViewMore = (Button)itemView.findViewById(R.id.btnViewMore);
            btnEdit = (Button)itemView.findViewById(R.id.btn_edit);
            btnUpdate = (Button)itemView.findViewById(R.id.btnUpdate);
            btnDelete = (Button)itemView.findViewById(R.id.btn_delete);

        }
    }

}























//public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {
//
//    ArrayList<FeaturedHelperClass> featuredLocations;
//
//    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations) {
//        this.featuredLocations = featuredLocations;
//    }
//
//    @NonNull
//    @Override
//    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
//        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
//        return featuredViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
//
//        FeaturedHelperClass featuredHelperClass = featuredLocations.get(position);
//
//        holder.placename.setText(featuredHelperClass.getPlacename());
//        holder.description.setText(featuredHelperClass.getDescription());
//        holder.iurl.setImageResource(featuredHelperClass.getIurl());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return featuredLocations.size();
//    }
//
//    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{
//
//
//        ImageView iurl;
//        TextView placename, description;
//
//        public FeaturedViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            //hooks
//            placename = itemView.findViewById(R.id.txt_placename);
//            description = itemView.findViewById(R.id.txt_description);
//            iurl = itemView.findViewById(R.id.img1);
//
//        }
//    }
//
//
//}
