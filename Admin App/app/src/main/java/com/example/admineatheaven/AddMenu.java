package com.example.admineatheaven;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddMenu extends AppCompatActivity {

    EditText name,description,cost;
    Spinner foodtype;
    Button submit;
    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    DatabaseReference myRef;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        name = findViewById(R.id.activity_add_menu_add_name);
        description = findViewById(R.id.activity_add_menu_add_description);
        cost = findViewById(R.id.activity_add_menu_cost);
        foodtype = findViewById(R.id.activity_add_menu_select_veg_nonveg);
        submit = findViewById(R.id.activity_add_menu_submit);


        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Hotel");
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myRef.child("menu").child(foodtype.getSelectedItem().toString()).child(myRef.push().getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("name").setValue(""+name.getText().toString().trim());
                        dataSnapshot.getRef().child("description").setValue(description.getText().toString().trim());
                        dataSnapshot.getRef().child("cost").setValue(cost.getText().toString().trim());
                        dataSnapshot.getRef().child("type").setValue(foodtype.getSelectedItem().toString().trim());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("User", databaseError.getMessage());
                    }
                });

            }
        });









    }
}
