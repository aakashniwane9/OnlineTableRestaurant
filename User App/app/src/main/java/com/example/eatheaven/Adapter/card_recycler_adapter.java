package com.example.eatheaven.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatheaven.Cart;
import com.example.eatheaven.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
public class card_recycler_adapter extends RecyclerView.Adapter<card_recycler_adapter.ViewHolder> {

    private List<card_recycler> listItems;
    private Context context;
    int quantity =0 ;




    public card_recycler_adapter(List<card_recycler> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_recycler_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        card_recycler card_recycler_list = listItems.get(position);
        viewHolder.name.setText(card_recycler_list.getName());
        viewHolder.cost.setText(card_recycler_list.getCost());
        viewHolder.type.setText(card_recycler_list.getType());
        viewHolder.key = card_recycler_list.getKey();

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

        TextView name,cost,type,count;
        String key;
        Button add,subs;

        FirebaseDatabase mDatabase;
        FirebaseAuth mAuth;
        DatabaseReference myRef;
        FirebaseUser firebaseUser;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.item_name);
            cost = (TextView) itemView.findViewById(R.id.item_cost);
            type = (TextView) itemView.findViewById(R.id.item_type);
            add = (Button)itemView.findViewById(R.id.item_add);
            subs = (Button)itemView.findViewById(R.id.item_substract);
            count = (TextView) itemView.findViewById(R.id.item_count);



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



            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int temp_count = Integer.parseInt(count.getText().toString());
                    count.setText("" + (temp_count+1));

                    quantity = temp_count;
                    quantity += 1;
                    Toast.makeText(context, "quantity"+""+quantity+"\nkey"+key, Toast.LENGTH_SHORT).show();

                    myRef.child("tables").child(firebaseUser.getUid()).child("cart").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().child("name").setValue(""+name.getText().toString().trim());
                            dataSnapshot.getRef().child("quantity").setValue(quantity);
                            dataSnapshot.getRef().child("cost").setValue(cost.getText().toString().trim());
                            dataSnapshot.getRef().child("status").setValue("pending");

                            myRef.child("cart").child(firebaseUser.getUid()).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    dataSnapshot.getRef().child("name").setValue(""+name.getText().toString().trim());
                                    dataSnapshot.getRef().child("quantity").setValue(quantity);
                                    dataSnapshot.getRef().child("cost").setValue(cost.getText().toString().trim());
                                    dataSnapshot.getRef().child("status").setValue("pending");


                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.d("User", databaseError.getMessage());
                                }
                            });

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("User", databaseError.getMessage());
                        }
                    });




                }
            });

            subs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt(count.getText().toString()) == 0){
                        Toast.makeText(context, "kitna kum karega bey !!!", Toast.LENGTH_SHORT).show();

                    }else{
                        int temp_count = Integer.parseInt(count.getText().toString());
                        count.setText("" + (temp_count-1));

                        quantity = temp_count;
                        quantity -= 1;

                        myRef.child("tables").child(firebaseUser.getUid()).child("cart").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                dataSnapshot.getRef().child("name").setValue(""+name.getText().toString().trim());
                                dataSnapshot.getRef().child("quantity").setValue(quantity);
                                dataSnapshot.getRef().child("cost").setValue(cost.getText().toString().trim());
                                dataSnapshot.getRef().child("status").setValue("pending");


                                myRef.child("cart").child(firebaseUser.getUid()).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        dataSnapshot.getRef().child("name").setValue(""+name.getText().toString().trim());
                                        dataSnapshot.getRef().child("quantity").setValue(quantity);
                                        dataSnapshot.getRef().child("cost").setValue(cost.getText().toString().trim());
                                        dataSnapshot.getRef().child("status").setValue("pending");

                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.d("User", databaseError.getMessage());
                                    }
                                });
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("User", databaseError.getMessage());
                            }
                        });


                    }
                }
            });







        }
    }
}
