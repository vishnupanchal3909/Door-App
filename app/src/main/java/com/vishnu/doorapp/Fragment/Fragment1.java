package com.vishnu.doorapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.vishnu.doorapp.Door.FDoor;
import com.vishnu.doorapp.R;

public class Fragment1 extends Fragment {

    CardView cardView1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview=(ViewGroup)inflater.inflate(R.layout.fragment1,container,false);


        cardView1=rootview.findViewById(R.id.fiber);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), FDoor.class);
                startActivity(intent);
            }
        });
        return rootview;
    }
}
