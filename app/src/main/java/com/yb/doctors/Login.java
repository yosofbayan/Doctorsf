package com.yb.doctors;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
    public static final String PHONE_NUMBER = "phone_number";
    public static final String VERIFICATION_ID = "verId";
    private EditText edtPhone, edtOTP;
    private Button generateOTPBtn;
    private ImageView logoImageView ,textUnderLogoImageView;
    private int reserveHeight = 0;
    private RelativeLayout relativeLayout;
    private FirebaseAuth mAuth;
    // variable for our text input
    // field for phone and OTP.
    // buttons for generating OTP and verifying OTP
    // string for storing our verification ID
    private String verificationId;
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
    public void initializeVariables(){
        FirebaseApp.initializeApp(this) ;
        mAuth = FirebaseAuth.getInstance();
        edtPhone = findViewById(R.id.login_phone_et);
        edtOTP = findViewById(R.id.login_pass);
        generateOTPBtn = findViewById(R.id.login_btn);
        relativeLayout =  findViewById(R.id.logo);
        textUnderLogoImageView = findViewById(R.id.text_under_logo);

    }
    public void setListeners (){
        generateOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is for checking weather the user
                // has entered his mobile number or not.
                if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                    // when mobile number text field is empty
                    // displaying a toast message.
                    Toast.makeText(Login.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    // if the text field is not empty we are calling our
                    // send OTP method for getting OTP from Firebase.
                    String phone = "+963" + edtPhone.getText().toString();

                    sendVerificationCode(phone);
                }
            }
        });


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeVariables();
        animate();
        setListeners();

    }

    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)		 // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)				 // Activity (for callback binding)
                        .setCallbacks(mCallBack)		 // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            Toast.makeText(Login.this, "onCodeSent", Toast.LENGTH_SHORT).show();
            verificationId = s;
            Intent i = new Intent(getApplicationContext(),Verification.class) ;
            i.putExtra(PHONE_NUMBER , "+963" + edtPhone.getText().toString()) ;
            i.putExtra(VERIFICATION_ID , verificationId) ;
            startActivity(i);
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                edtOTP.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
           //     verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.

}
