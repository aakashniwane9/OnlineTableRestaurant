package com.example.eatheaven.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eatheaven.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class TrackFoodAdapter extends RecyclerView.Adapter<TrackFoodAdapter.ViewHolder> {


    private List<TrackFoodList> listItems;
    private Context context;



    public TrackFoodAdapter(List<TrackFoodList> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trackfood_recycler_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        TrackFoodList trackFoodList = listItems.get(position);
        viewHolder.name.setText(trackFoodList.getName());
        viewHolder.status.setText(trackFoodList.getStatus());
        viewHolder.quantity.setText(trackFoodList.getQuantity());

        viewHolder.mDatabase = FirebaseDatabase.getInstance();
        viewHolder.myRef = viewHolder.mDatabase.getReference("Hotel");
        viewHolder.mAuth = FirebaseAuth.getInstance();
        viewHolder.firebaseUser = viewHolder.mAuth.getCurrentUser();
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,status,quantity;

        FirebaseDatabase mDatabase;
        FirebaseAuth mAuth;
        DatabaseReference myRef;
        FirebaseUser firebaseUser;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.trackfood_recycler_itemname);
            status = (TextView) itemView.findViewById(R.id.trackfood_recycler_itemstatus);
            quantity = (TextView)itemView.findViewById(R.id.trackfood_recycler_itemquantity);



//            display_picture.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context,OthersProfile.class);
//                    intent.putExtra("key",key);
//                    v.getContext().startActivity(intent);
//                }
//            });
//
//            image.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i=new Intent(context, Comments.class);
//                    i.putExtra("key",key);
//                    v.getContext().startActivity(i);
//                }
//            });
//
//            comment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i=new Intent(context, Comments.class);
//                    i.putExtra("key",key);
//                    v.getContext().startActivity(i);
//                }
//            });
        }
    }

}
