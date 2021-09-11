package com.yb.doctors;

import androidx.appcompat.app.AppCompatActivity;


import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;


public class Login extends AppCompatActivity  {
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private ImageView logoImageView ,textUnderLogoImageView;
    private int reserveHeight = 0;
    RelativeLayout relativeLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeVariables();
        animate();


    }



    public void initializeVariables(){
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.pass);
        loginButton = findViewById(R.id.login_btn);
        relativeLayout =  findViewById(R.id.logo);
        textUnderLogoImageView = findViewById(R.id.text_under_logo);

    }
    public void animate(){

        KeyboardVisibilityEvent.setEventListener(
                this,
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if(reserveHeight==0)
                            reserveHeight = relativeLayout.getMeasuredHeight();

                        if (isOpen) {

                            relativeLayout.setVisibility(View.INVISIBLE);
                           ValueAnimator anim = ValueAnimator.ofInt(relativeLayout.getMeasuredHeight() ,0);
                            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int val = (Integer) valueAnimator.getAnimatedValue();
                                    ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
                                    layoutParams.height = val;
                                    relativeLayout.setLayoutParams(layoutParams);
                                }


                            });
                            anim.setDuration(500);
                            anim.start();
                        }else{


                            relativeLayout.setVisibility(View.VISIBLE);
                            ValueAnimator anim = ValueAnimator.ofInt( relativeLayout.getMeasuredHeight(), +reserveHeight);
                            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int val = (Integer) valueAnimator.getAnimatedValue();
                                    ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
                                    layoutParams.height = val;
                                    relativeLayout.setLayoutParams(layoutParams);

                                }


                            });
                            anim.setDuration(500);
                            anim.start();

                        }
                    }
                });


    }

}