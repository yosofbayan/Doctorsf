package com.yb.doctors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class Verification extends AppCompatActivity {
    private TextView countDownTime;
    private String counter   ;
    private Button verifyOTPBtn;
    private EditText otpCode ;
    private String verificationId;
    private Intent intent ;
    private FirebaseAuth mAuth;
    private String number ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        init();
        setCountDownTime();
        setListeners();
    }
    public void setListeners () {
        verifyOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the OTP text field is empty or not.
                if (TextUtils.isEmpty(otpCode.getText().toString())) {
                    // if the OTP text field is empty display
                    // a message to user to enter OTP
                    Toast.makeText(Verification.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    // if OTP field is not empty calling
                    // method to verify the OTP.
                    verifyCode(otpCode.getText().toString());
                }
            }
        });
    }
    public void setCountDownTime(){
        new CountDownTimer(120000, 1000){
            public void onTick(long millisUntilFinished){
                counter = " " + millisUntilFinished / 1000;

                countDownTime.setText
                        (new java.text.SimpleDateFormat("mm:ss")
                                .format(new java.util.Date (millisUntilFinished)));


            }
            public void onFinish(){
                countDownTime.setText("00:00");
            }
        }.start();
    }
    public void init () {
        mAuth = FirebaseAuth.getInstance();
        countDownTime = findViewById(R.id.time);
        verifyOTPBtn = findViewById(R.id.verlog);
        otpCode = findViewById(R.id.otpCodeEt) ;
        intent = getIntent();
        Toast.makeText(this, "" + verificationId, Toast.LENGTH_SHORT).show();
        number = intent.getStringExtra(Login.PHONE_NUMBER) ;


    }
    private void verifyCode(String code) {
        verificationId = Login.VERIFICATION_ID ;
        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            Intent i = new Intent(Verification.this, MainPageActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(Verification.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}