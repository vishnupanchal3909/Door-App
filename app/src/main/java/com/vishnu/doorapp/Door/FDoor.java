package com.vishnu.doorapp.Door;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.vishnu.doorapp.R;

public class FDoor extends AppCompatActivity {

    ImageView addFiber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdoor2);
        addFiber=findViewById(R.id.addFiber);

        addFiber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddFIDoor.class);
                startActivity(intent);
            }
        });
    }
}