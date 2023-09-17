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

public class Register extends AppCompatActivity {

    TextInputEditText Name,Email,phone,password;
    TextView submit,signIn;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name=findViewById(R.id.RegisterName);
        Email=findViewById(R.id.Registeremail);
        phone=findViewById(R.id.Registerphone);
        password=findViewById(R.id.Registerpassword);
        submit=findViewById(R.id.submit);
        signIn=findViewById(R.id.RegisterSignIN);

        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://door-app-2f391-default-rtdb.firebaseio.com/");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Name.getText().toString().isEmpty() || Email.getText().toString().isEmpty() ||
                        phone.getText().toString().isEmpty() || password.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Please,Enter Detaile", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phone.getText().toString())){
                                Toast.makeText(getApplicationContext(), "Already Exist", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("User").child(phone.getText().toString()).child("fullName").setValue(Name.getText().toString());
                                databaseReference.child("User").child(phone.getText().toString()).child("email").setValue(Email.getText().toString());
                                databaseReference.child("User").child(phone.getText().toString()).child("phone").setValue(phone.getText().toString());
                                databaseReference.child("User").child(phone.getText().toString()).child("passowrd").setValue(password.getText().toString());
                                Toast.makeText(getApplicationContext(), "Register Sucessfully....", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
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

    }
}