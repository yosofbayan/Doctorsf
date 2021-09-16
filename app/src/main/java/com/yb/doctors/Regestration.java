package com.yb.doctors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Regestration extends AppCompatActivity {
    private EditText nameEditText,cityEditText,phoneEditText,passwordEditText,confirmPasswordEditText;
    private CheckBox checkBox;
    private TextView policyTextView;
    private Button button;
    private ImageView closeIconInNameEditTextImageView,checkIconInNameEditTextImageView,
        closeIconInPasswordEditTextImageView, checkIconInPasswordEditTextImageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);

         initializeVariables();

         //apply some conditions on the EditTexts
         View[] views = {closeIconInNameEditTextImageView,checkIconInNameEditTextImageView};
         applyConditionsOnEditText(nameEditText ,views , 4 );
         View[] views2 = {closeIconInPasswordEditTextImageView,checkIconInPasswordEditTextImageView};
         applyConditionsOnEditText(passwordEditText ,views2 ,8);

    }

    public void initializeVariables(){
        nameEditText = findViewById(R.id.registration_user_name_et);
        cityEditText = findViewById(R.id.registration_city_et);
        phoneEditText = findViewById(R.id.registration_phone_number_et);
        passwordEditText = findViewById(R.id.registration_password_et);
        confirmPasswordEditText = findViewById(R.id.registration_confirm_password_et);
        checkBox = findViewById(R.id.registration_cb);
        policyTextView = findViewById(R.id.registration_policy_tv);
        button = findViewById(R.id.registration_btn);
        closeIconInNameEditTextImageView = findViewById(R.id.close_icon_in_name_et);
        closeIconInPasswordEditTextImageView = findViewById(R.id.close_icon_in_password_et);
        checkIconInNameEditTextImageView = findViewById(R.id.check_icon_in_name_et);
        checkIconInPasswordEditTextImageView = findViewById(R.id.check_icon_in_password_et);

    }

    public void applyConditionsOnEditText(EditText editText ,View views [] , int con){

            views[0].setVisibility(View.GONE);
            views[1].setVisibility(View.GONE);

       editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                    if(s.toString().trim().length()< con && s.toString().trim().length() > 0){
                        views[1].setVisibility(View.INVISIBLE);
                        views[0].setVisibility(View.VISIBLE);
                    }else if(s.toString().trim().length() >=con){
                        views[0].setVisibility(View.INVISIBLE);
                        views[1].setVisibility(View.VISIBLE);
                    }else{
                        views[0].setVisibility(View.GONE);
                        views[1].setVisibility(View.GONE);
                    }


            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

    }
}