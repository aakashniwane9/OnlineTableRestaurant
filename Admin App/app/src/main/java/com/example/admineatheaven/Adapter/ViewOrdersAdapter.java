package com.example.admineatheaven.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admineatheaven.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ViewOrdersAdapter extends RecyclerView.Adapter<ViewOrdersAdapter.ViewHolder> {

    private List<ViewOrdersList> listItems;
    private Context context;



    public ViewOrdersAdapter(List<ViewOrdersList> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vieworders_recycler_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        ViewOrdersList viewOrdersList = listItems.get(position);
        viewHolder.name.setText(viewOrdersList.getName());
        viewHolder.status.setText(viewOrdersList.getStatus());
        viewHolder.quantity.setText(viewOrdersList.getQuantity());

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

            name = (TextView) itemView.findViewById(R.id.view_orders_name);
            status = (TextView) itemView.findViewById(R.id.view_orders_status);
            quantity = (TextView)itemView.findViewById(R.id.view_orders_quantity);

            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Order accepted", Toast.LENGTH_SHORT).show();
                    if (status.getText().equals("pending"))
                    {
                        status.setText("accepted");

                        myRef.child("cart").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for(DataSnapshot ds:dataSnapshot.getChildren())
                                {
                                    Toast.makeText(context, ""+ds.child("table").getValue(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("User", databaseError.getMessage());
                            }
                        });








                    }else
                        status.setText("pending");
                }
            });
        }
    }

}
