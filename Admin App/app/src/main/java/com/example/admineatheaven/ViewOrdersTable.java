package com.example.admineatheaven;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.admineatheaven.Adapter.ViewOrdersAdapter;
import com.example.admineatheaven.Adapter.ViewOrdersList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewOrdersTable extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference("Hotel");
    String key;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ViewOrdersList> listItems;
    ViewOrdersList listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table1);

        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Hotel");
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        recyclerView = (RecyclerView)findViewById(R.id.activity_home_view_orders_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewOrdersTable.this));
        listItems=new ArrayList<>();

        myRef.child("tables").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {

                    if (ds.child("table").getValue().equals(""+1)){
                        key = ds.getKey();
                        Toast.makeText(ViewOrdersTable.this, ""+key, Toast.LENGTH_SHORT).show();

                        myRef.child("cart").child(key).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot ds:dataSnapshot.getChildren())
                                {
                                    listItem= new ViewOrdersList("" + ds.child("name").getValue(),
                                                "" + ds.child("quantity").getValue(),
                                                ""+ds.child("status").getValue());
                                        listItems.add(listItem);
                                }
                                adapter=new ViewOrdersAdapter(listItems, ViewOrdersTable.this);
                                recyclerView.setAdapter(adapter);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(ViewOrdersTable.this, "Check your Internet", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    else
                        Toast.makeText(ViewOrdersTable.this, "No Orders for this table", Toast.LENGTH_SHORT).show();
                }
                adapter=new ViewOrdersAdapter(listItems, ViewOrdersTable.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewOrdersTable.this, "Check your Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
