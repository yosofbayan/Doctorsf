package com.yb.doctors;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yb.doctors.adapters.FavouriteAdapter;
import com.yb.doctors.model.Doctor;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        LinearLayout back = findViewById(R.id.favourite_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<Doctor> doctors = new ArrayList<>();
        for(int i=0;i<10;i++)
            doctors.add(new Doctor());
        FavouriteAdapter favouriteAdapter = new FavouriteAdapter(doctors);
        RecyclerView recyclerView = findViewById(R.id.favourite_recycler_view);
        recyclerView.setAdapter(favouriteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}