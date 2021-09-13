package com.yb.doctors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.suke.widget.SwitchButton;

public class DoctorInfo extends AppCompatActivity {
    private TextView rejectedCounterTextView, acceptanceCounterTextView,doctorNameTextView,specialistAtTextView,cityTextView
    ,stateTextView,subscriptionDateTextView,expirationDateTextView,daysLeftTextView,logOutTextView;

    private ImageView doctorPhotoImageView;
    private SwitchCompat switchCompat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);
        initializeVariables();
    }




    public void initializeVariables () {

        doctorNameTextView = findViewById(R.id.doctor_info_doctor_name);
        specialistAtTextView = findViewById(R.id.doctor_info_specialist_at);
        cityTextView = findViewById(R.id.doctor_info_city);
        stateTextView = findViewById(R.id.doctor_info_state);
        subscriptionDateTextView = findViewById(R.id.doctor_info_date_of_subscription);
        expirationDateTextView = findViewById(R.id.doctor_info_date_of_expiration);
        daysLeftTextView = findViewById(R.id.doctor_info_days_left);
        rejectedCounterTextView = findViewById(R.id.doctor_info_rejects_counter);
        acceptanceCounterTextView = findViewById(R.id.doctor_info_acceptance_counter);
        logOutTextView = rejectedCounterTextView = findViewById(R.id.doctor_info_rejects_counter);
        acceptanceCounterTextView = findViewById(R.id.doctor_info_log_out);
        switchCompat =  findViewById(R.id.switch_button);
        doctorPhotoImageView = findViewById(R.id.doctor_info_doctor_photo);

    }



}
