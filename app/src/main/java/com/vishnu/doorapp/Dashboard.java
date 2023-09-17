package com.vishnu.doorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class Dashboard extends AppCompatActivity {

    private  final  int[] picture={
            R.drawable.first,R.drawable.second,R.drawable.third,R.drawable.fourth,R.drawable.fifth
    };
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar=(androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        CarouselView carouselView=(CarouselView)findViewById(R.id.MainFragement_NewsCarousel);
        carouselView.setPageCount(picture.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(picture[position]);
            }
        });

    }
}