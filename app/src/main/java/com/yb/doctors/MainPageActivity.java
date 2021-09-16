package com.yb.doctors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import java.util.List;
import java.util.Map;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yb.doctors.adapters.SectorsAdapter;
import com.yb.doctors.model.Sector;

<<<<<<< app/src/main/java/com/yb/doctors/MainPageActivity.java

public class MainPageActivity extends AppCompatActivity {

    private RecyclerView specialtiesRecyclerView,topRecyclerView;
    private final Handler sliderHandler = new Handler();
    private ViewPager2 viewPager2,centersViewPagers2;
    private int page;
    int forward = 1;
    private ImageView menuImageView , indexImageView ;
    private ArrayList<Sector> sectors ;
    private ArrayList<String> photos;
    private ArrayList<Doctor> doctors;
    private ArrayList<FourHealthCenterObjects> fourHealthCenterObjectsList;
    private SectorsAdapter sectorsAdapter;
    private PhotoAdapter photoAdapter;
    private TopsAdapter topsAdapter;
    private HealthCentersAdapter healthCentersAdapter;
    private DrawerLayout drawerLayout;
    ArrayList<String> imagelist;
    StorageReference root;

    public void getHome_aDataFromFirebase () {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Home").child("Home_A").child("region_1");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of sectors in datasnapshot
                        getHome_aDataFromMap((Map<String,Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
        ref.keepSynced(true);
    }
    private void getHome_aDataFromMap (Map<String,Object> sectors) {
        int i = 0;
        ArrayList<String> phoneNumbers = new ArrayList<>();
        //iterate through each sector, ignoring their UID
        for (Map.Entry<String, Object> entry : sectors.entrySet()){
            //Get sector map
            Map<String,String> sector = (Map<String, String>) entry.getValue();

        }


    }

    private void getSectorsDataFromMap (Map<String,Object> sectors) {
        this.sectors.clear();
        int i = 0;
        Log.d("SECTORSINFO" , "SECTORS INFORMATIONS ") ;
        ArrayList<String> phoneNumbers = new ArrayList<>();
        //iterate through each sector, ignoring their UID
        for (Map.Entry<String, Object> entry : sectors.entrySet()){
            //Get sector map
            Map<String,String> sector = (Map<String, String>) entry.getValue();
            Sector s = new Sector(sector.get("Name_AR")
            ,sector.get("Name_EN") ,sector.get("Name_KU")
            ,sector.get("Name_TR"),entry.getKey(),sector.get("key_code")
            ,sector.get("number")) ;
            this.sectors.add(s) ;
            Log.d("SECTOR "+i+" INFO : " ,
            "name Ar : " + s.getNameAr()
            + "\nname En : " + s.getNameEn()
            +"\nname Ku : " + s.getNameKu()
            +"\nname Tr : " + s.getNameTr()
            +"\nimage key : "+s.getImageKey()
            +"\nkey code : "+s.getKeyCode()
            +"\nnumber : " + s.getNumber()) ;

        }

        System.out.println("HI   " +sectors.toString());
    }
    public void getSectorsDataFromFirebase () {
        //Get datasnapshot at your "sectors" root node
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Home").child("specialties");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of sectors in datasnapshot
                        getSectorsDataFromMap((Map<String,Object>) dataSnapshot.getValue());
                        sectorsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
                ref.keepSynced(true);


    }
    public void init () {
        sectors = new ArrayList<>() ;
        recyclerView = findViewById(R.id.main_page_specialties_recycler_view);
        sectorsAdapter =new SectorsAdapter(sectors);
        recyclerView.setAdapter(sectorsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainPageActivity.this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        init();
        getSectorsDataFromFirebase();
        initializeVariables();
        setPhotoAdapter();
        setSectorsAdapter();
        setTopsAdapter();
        setHealthCentersAdapter();
        setOnClickListeners();

    }

    }
