package com.example.madfullcrud;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull MainModel model) {
        holder.placename.setText(model.getPlacename());
        holder.placetype.setText(model.getPlacetype());
        holder.ownername.setText(model.getOwnername());
        holder.address.setText(model.getAddress());
        holder.email.setText(model.getEmail());
        holder.phone1.setText(model.getPhone1());
        holder.phone2.setText(model.getPhone2());
        holder.description.setText(model.getDescription());

        Glide.with(holder.img.getContext())
                .load(model.getIurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);


        //update code
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();

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

        //Delete code
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.placename.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be Undo.");

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView placename, placetype, address, description, email, ownername, phone1, phone2;

        Button btnUpdate, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            placename=(TextView)itemView.findViewById(R.id.place_name);
            placetype=(TextView)itemView.findViewById(R.id.place_type);
            ownername=(TextView)itemView.findViewById(R.id.ownername);
            address=(TextView)itemView.findViewById(R.id.address);
            email=(TextView)itemView.findViewById(R.id.email);
            phone1=(TextView)itemView.findViewById(R.id.phone1);
            phone2=(TextView)itemView.findViewById(R.id.phone2);
            description=(TextView)itemView.findViewById(R.id.description);

            btnUpdate = (Button)itemView.findViewById(R.id.btn_edit);
            btnDelete = (Button)itemView.findViewById(R.id.btn_delete);
        }
    }
}
