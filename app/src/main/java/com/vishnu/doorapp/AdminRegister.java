package com.vishnu.doorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminRegister extends AppCompatActivity {

    TextInputEditText Name,email,shopkepperId,phone,password;
    TextView submit,signIn;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        Name=findViewById(R.id.ADMINName);
        email=findViewById(R.id.ADMINemail);
        shopkepperId=findViewById(R.id.ADMINshop);
        phone=findViewById(R.id.ADMINphone);
        password=findViewById(R.id.ADMINpassword);
        submit=findViewById(R.id.Adminsubmit);
        signIn=findViewById(R.id.ADMINSignIN);
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://door-app-2f391-default-rtdb.firebaseio.com/");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || shopkepperId.getText().toString().isEmpty() ||
                        phone.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter Details..", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(shopkepperId.getText().toString())){
                                Toast.makeText(getApplicationContext(), "Admin Is Already Exits", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("Admin").child(shopkepperId.getText().toString()).child("AdminName").setValue(Name.getText().toString());
                                databaseReference.child("Admin").child(shopkepperId.getText().toString()).child("AdminEmail").setValue(email.getText().toString());
                                databaseReference.child("Admin").child(shopkepperId.getText().toString()).child("AdminShopkepperId").setValue(shopkepperId.getText().toString());
                                databaseReference.child("Admin").child(shopkepperId.getText().toString()).child("Adminphone").setValue(phone.getText().toString());
                                databaseReference.child("Admin").child(shopkepperId.getText().toString()).child("AdminPassword").setValue(password.getText().toString());
                                Toast.makeText(getApplicationContext(), "Admin Registertion is Sucessfully...", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Admin.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Admin.class));;
            }
        });
    }
}