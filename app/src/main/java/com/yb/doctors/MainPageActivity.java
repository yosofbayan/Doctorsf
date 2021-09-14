package com.yb.doctors;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.yb.doctors.adapters.HealthCentersAdapter;
import com.yb.doctors.adapters.PhotoAdapter;
import com.yb.doctors.adapters.SectorsAdapter;
import com.yb.doctors.adapters.TopsAdapter;
import com.yb.doctors.model.Doctor;
import com.yb.doctors.model.FourHealthCenterObjects;
import com.yb.doctors.model.Sector;
import androidx.drawerlayout.widget.DrawerLayout;
import java.util.ArrayList;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawer_layout);
        initializeVariables();
        setPhotoAdapter();
        setSectorsAdapter();
        setTopsAdapter();
        setHealthCentersAdapter();
        setOnClickListeners();
    }

    public void initializeVariables(){
        menuImageView = findViewById(R.id.main_page_menu);
        indexImageView = findViewById(R.id.main_page_index);
        viewPager2 = findViewById(R.id.main_page_view_pager);
        specialtiesRecyclerView = findViewById(R.id.main_page_specialties_recycler_view);
        topRecyclerView = findViewById(R.id.main_page_top_recycler_view);
        centersViewPagers2 = findViewById(R.id.main_page_health_centers_view_pager);
        drawerLayout = findViewById(R.id.drawer_layout);

    }

   private void setOnClickListeners(){

        menuImageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (!drawerLayout.isDrawerOpen(Gravity.START)) drawerLayout.openDrawer(Gravity.START);
                else drawerLayout.closeDrawer(Gravity.END);
            }
        });
       LinearLayout profile , medicalFile , favourite , healthCenters , doctor , settings , about ,report, logout;

       NavigationView navigationView = findViewById(R.id.nav_view);
       View header = navigationView.getHeaderView(0);

       ImageView userPhoto = header.findViewById(R.id.nav_header_user_photo);
       TextView userName = header.findViewById(R.id.nav_header_name);
       profile = header.findViewById(R.id.nav_view_profile);
       medicalFile = header.findViewById(R.id.nav_view_medical_file);
       favourite = header.findViewById(R.id.nav_view_favourite);
       healthCenters = header.findViewById(R.id.nav_view_health_centers);
       doctor = header.findViewById(R.id.nav_view_doctor);
       settings = header.findViewById(R.id.nav_view_settings);
       about = header.findViewById(R.id.nav_view_about);
       report = header.findViewById(R.id.nav_view_report);
       logout = header.findViewById(R.id.nav_view_logout);

       profile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       medicalFile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

       favourite.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


           }
       });
       healthCenters.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       doctor.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       settings.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       about.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       report.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

   }

    private  void setPhotoAdapter(){

        //Temp Data
        photos= new ArrayList();

        for(int i=0 ; i < 10 ; i++)
            photos.add("");

        photoAdapter =new PhotoAdapter(photos);
        viewPager2.setAdapter(photoAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                page = position;


            }

        });



    }


    private void setCurrentIem(int item ,int currentItem, long duration, int pagePxWidth ){
        Interpolator interpolator = new AccelerateDecelerateInterpolator();



        int pxToDrag = pagePxWidth * (item - currentItem);
        ValueAnimator animator = ValueAnimator.ofInt(0, pxToDrag);
        final int[] previousValue = {0};
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int  currentValue = (int) valueAnimator.getAnimatedValue();
                float currentPxToDrag = (float)(currentValue - previousValue[0]);
                viewPager2.fakeDragBy(forward * currentPxToDrag);
                previousValue[0] = currentValue;
            }
        });

       animator.addListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animation) {
               viewPager2.beginFakeDrag();
           }

           @Override
           public void onAnimationEnd(Animator animation) {
               viewPager2.endFakeDrag();
           }

           @Override
           public void onAnimationCancel(Animator animation) {

           }

           @Override
           public void onAnimationRepeat(Animator animation) {

           }
       });
       animator.setInterpolator(interpolator);
       animator.setDuration(duration);
        animator.start();
    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {

            if (photoAdapter.getItemCount()-1 == page) {
                 forward = -1;
            } else if (page == 0) {
                forward = 1;
            }


            setCurrentIem(page-1,page,1000,viewPager2.getWidth());
            sliderHandler.postDelayed(this, 4000);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }


    private void setSectorsAdapter(){
        //Temp Data
        sectors = new ArrayList();

        for(int i=0 ; i < 6 ; i++)
            sectors.add(new Sector());

        sectorsAdapter =new SectorsAdapter(sectors);
        specialtiesRecyclerView.setAdapter(sectorsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainPageActivity.this,RecyclerView.HORIZONTAL,false);
        specialtiesRecyclerView.setLayoutManager(linearLayoutManager);

    }

    private void setTopsAdapter(){
        doctors = new ArrayList<>();
        for(int i=0 ; i < 6 ; i++)
            doctors.add(new Doctor());

        topsAdapter =new TopsAdapter(doctors);
        topRecyclerView.setAdapter(topsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainPageActivity.this);
        topRecyclerView.setLayoutManager(linearLayoutManager);

    }

    private void setHealthCentersAdapter(){


       //Temp Data
       fourHealthCenterObjectsList = new ArrayList<>();

       for(int i=0 ; i < 4 ; i++) {

           fourHealthCenterObjectsList.add(new FourHealthCenterObjects());
       }

       healthCentersAdapter =new HealthCentersAdapter(fourHealthCenterObjectsList);
       centersViewPagers2.setAdapter(healthCentersAdapter);

       centersViewPagers2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
           @Override
           public void onPageSelected(int position) {
               super.onPageSelected(position);



           }

       });


   }

}
