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

public class GenerateBillAdapter extends RecyclerView.Adapter<GenerateBillAdapter.ViewHolder>{



    private List<GenerateBillList> listItems;
    private Context context;



    public GenerateBillAdapter(List<GenerateBillList> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.generatebill_recycler_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        GenerateBillList generateBillList = listItems.get(position);
        viewHolder.name.setText(generateBillList.getName());
        viewHolder.cost.setText(generateBillList.getCost());
        viewHolder.quantity.setText(generateBillList.getQuantity());

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

        TextView name,cost,quantity;

        FirebaseDatabase mDatabase;
        FirebaseAuth mAuth;
        DatabaseReference myRef;
        FirebaseUser firebaseUser;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.generatebill_recycler_item_name);
            cost = (TextView) itemView.findViewById(R.id.generatebill_recycler_item_cost);
            quantity = (TextView)itemView.findViewById(R.id.generatebill_recycler_item_quantity);



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
