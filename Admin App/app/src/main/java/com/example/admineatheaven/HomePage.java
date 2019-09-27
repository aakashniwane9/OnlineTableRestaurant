
package com.example.admineatheaven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        CardView cardaddmenu,orders;


        cardaddmenu = findViewById(R.id.activity_home_add_menu);
        cardaddmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePage.this,AddMenu.class));

            }
        });

        orders = findViewById(R.id.activity_home_view_orders);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePage.this, ViewOrdersTable.class));

            }
        });





    }
}
