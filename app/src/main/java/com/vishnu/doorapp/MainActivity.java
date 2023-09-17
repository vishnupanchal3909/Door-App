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

public class MainActivity extends AppCompatActivity {
    TextInputEditText phoneTxt,passwordTxt;
    TextView signIn,signUp,AdminLogin;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneTxt=findViewById(R.id.phone);
        passwordTxt=findViewById(R.id.password);
        signIn=findViewById(R.id.signIN);
        signUp=findViewById(R.id.signUp);
        AdminLogin=findViewById(R.id.AdminGo);
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://door-app-2f391-default-rtdb.firebaseio.com/");
//
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phoneTxt.getText().toString().isEmpty() || passwordTxt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please,Enter Detaile", Toast.LENGTH_SHORT).show();
                }else {

                    databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phoneTxt.getText().toString())){

                                final String getPassword= snapshot.child(phoneTxt.getText().toString()).child("passowrd").getValue(String.class);

                                if(getPassword.equals(passwordTxt.getText().toString())){

                                    startActivity(new Intent(getApplicationContext(),Dashboard.class));

                                    Toast.makeText(getApplicationContext(), "Login Sucessfully...", Toast.LENGTH_SHORT).show();

                                }
                                else{

                                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {

                                Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
//
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
//
        AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Admin.class));
                finish();
            }
        });
    }
}