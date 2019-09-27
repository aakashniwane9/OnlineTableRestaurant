package com.example.eatheaven;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.eatheaven.Adapter.CartAdapter;
import com.example.eatheaven.Adapter.CartList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class Cart extends AppCompatActivity {


    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    String key,quantity;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CartList> listItems;
    CartList listItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Hotel");
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        recyclerView = (RecyclerView)findViewById(R.id.activity_cart_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
        listItems=new ArrayList<>();


        myRef.child("tables").child(firebaseUser.getUid()).child("cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    listItem= new CartList("" + ds.child("name").getValue(),
                            "" + ds.child("cost").getValue(),
                            "" + ds.child("status").getValue(),
                            ""+ds.child("quantity").getValue());
                    listItems.add(listItem);
                }
                adapter=new CartAdapter(listItems, Cart.this);
                recyclerView.setAdapter(adapter);


//                String datausername = "" + dataSnapshot.child(username).child("contact").getValue();
//                String datapassword = "" +dataSnapshot.child(username).child("password").getValue();
//                if (username.equals(datausername) && password.equals(datapassword)) {
//
//                    Intent intent = new Intent(Login.this, HomePage.class);
//                    startActivity(intent);
//                    finish();
//                }else
//                    Toast.makeText(Login.this, "Credentials Wrong...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Cart.this, "Check your Internet", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
