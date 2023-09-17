package com.vishnu.doorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.vishnu.doorapp.Fragment.Fragment1;
import com.vishnu.doorapp.Fragment.Fragment2;
import com.vishnu.doorapp.Fragment.Fragment3;

public class AdminDashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        toolbar=(androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new Fragment1()).commit();
        bottomNavigationView=findViewById(R.id.bottom_navigation_learn);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new Fragment1()).commit();
                        break;

                    case R.id.yourorder:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new Fragment2()).commit();
                        break;

                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new Fragment3()).commit();
                        break;
                }
                return true;
            }
        });

    }

}