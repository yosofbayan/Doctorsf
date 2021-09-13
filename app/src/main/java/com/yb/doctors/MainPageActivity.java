package com.yb.doctors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.yb.doctors.adapters.SectorsAdapter;
import com.yb.doctors.model.Sector;

import java.util.ArrayList;

public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ArrayList sectors = new ArrayList();

        for(int i=0 ; i < 10 ; i++)
            sectors.add(new Sector());



               RecyclerView recyclerView = findViewById(R.id.main_page_specialties_recycler_view);
                SectorsAdapter sectorsAdapter =new SectorsAdapter(sectors);
                recyclerView.setAdapter(sectorsAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainPageActivity.this,RecyclerView.HORIZONTAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);




    }
}
