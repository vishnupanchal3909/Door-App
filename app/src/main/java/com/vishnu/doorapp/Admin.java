package com.vishnu.doorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin extends AppCompatActivity {

    TextInputEditText phoneAdmin,passwordAdmin;
    TextView signInAdmin,NotAdmin,Register;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        phoneAdmin=findViewById(R.id.Adminphone);
        passwordAdmin=findViewById(R.id.Adminpassword);
        signInAdmin=findViewById(R.id.AdminsignIN);
        NotAdmin=findViewById(R.id.Admin);
        Register=findViewById(R.id.RegisterAdmin);
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://door-app-2f391-default-rtdb.firebaseio.com/");

        signInAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phoneAdmin.getText().toString().isEmpty() || passwordAdmin.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please,Enter Detaile", Toast.LENGTH_SHORT).show();
                }else {

                    databaseReference.child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phoneAdmin.getText().toString())){

                                final String getPassword= snapshot.child(phoneAdmin.getText().toString()).child("AdminPassword").getValue(String.class);

                                if(getPassword.equals(passwordAdmin.getText().toString())){

                                    Toast.makeText(getApplicationContext(), "Admin Login Sucessfully...", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(getApplicationContext(),AdminDashboard.class));


                                }
                                else{

                                    Toast.makeText(getApplicationContext(), "Wrong Password1", Toast.LENGTH_SHORT).show();

                                }
                            }
                            else {

                                Toast.makeText(getApplicationContext(), "Wrong Password2", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

        NotAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();

            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdminRegister.class));

            }
        });


    }
}