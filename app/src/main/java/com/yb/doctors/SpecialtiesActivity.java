package com.yb.doctors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.yb.doctors.adapters.SectorsAdapter;
import com.yb.doctors.adapters.SpecialitiesAdapter;
import com.yb.doctors.model.Sector;

import java.util.ArrayList;

public class SpecialtiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialties);
        setSpecialtiesAdapter();
    }


    private void setSpecialtiesAdapter(){
       /* //Temp Data
        ArrayList<Sector> specialties = new ArrayList<>();

        for(int i=0 ; i < 16 ; i++)
       //     specialties.add(new Sector());

        SpecialitiesAdapter specialitiesAdapter=new SpecialitiesAdapter(specialties);

        RecyclerView recyclerView = findViewById(R.id.specialties_recycler_view);
        recyclerView.setAdapter(specialitiesAdapter);
        RecyclerViewMargin decoration = new RecyclerViewMargin(32, 3,this);
        recyclerView.addItemDecoration(decoration);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);*/

    }

}
