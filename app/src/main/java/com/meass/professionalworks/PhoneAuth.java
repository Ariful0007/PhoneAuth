package com.meass.professionalworks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneAuth extends AppCompatActivity {
String[] code ={"+880","+91"};
FirebaseAuth firebaseAuth;
TextInputEditText editTextCountryCode,editTextPhone;
AppCompatButton buttonContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);
        firebaseAuth=FirebaseAuth.getInstance();
        editTextCountryCode=findViewById(R.id.editTextCountryCode);
        editTextPhone=findViewById(R.id.editTextPhone); buttonContinue=findViewById(R.id.buttonContinue);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=editTextCountryCode.getText().toString();
                String phone=editTextPhone.getText().toString();
                if (TextUtils.isEmpty(code)||TextUtils.isEmpty(phone)) {
                    Toast.makeText(PhoneAuth.this, "Enter all information", Toast.LENGTH_SHORT).show();
                }
                else {
                    String mobile=code+""+phone;
                    Intent intent=new Intent(getApplicationContext(),VerifyPhoneActivity.class);
                    intent.putExtra("mobile",mobile);
                    startActivity(intent);
                }
            }
        });


    }
}