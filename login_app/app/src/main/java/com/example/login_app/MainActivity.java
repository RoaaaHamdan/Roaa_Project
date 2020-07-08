package com.example.login_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
 private EditText editTextPassword;
 private EditText editTextEmail;
 private Button ButtonLogin;
 private TextView TextViewRegistrationLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail=findViewById(R.id.email_text);
        editTextPassword=findViewById(R.id.password_text);
        ButtonLogin = findViewById(R.id.login_button);
        editTextPassword.addTextChangedListener(loginTextWatcher);
        editTextEmail.addTextChangedListener(loginTextWatcher);
        TextViewRegistrationLink=findViewById(R.id.registrationLink);

        TextViewRegistrationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegistrationPage.class );
                startActivity(intent);
            }
        });



        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString();
                String pass = editTextPassword.getText().toString();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userInfo",0);
                String Email = sharedPreferences.getString("emailKey","" );
                String Pass = sharedPreferences.getString("passKey","");
                if (email.equals(Email) &&(pass.equals(Pass)) ){

                    Intent intent = new Intent(MainActivity.this,DashboardPage.class);
                    startActivity(intent);

                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setIcon(R.drawable.ic_sentiment_dissatisfied_black_24dp);
                    builder.setTitle("Something wrong , please try again !");
                    builder.setPositiveButton("Ok", null);
                    AlertDialog dialog =builder.create();
                    dialog.show();


                }


            }
        });


    }
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String InputPassword = editTextPassword.getText().toString();
            String InputEmail = editTextEmail.getText().toString().trim();

            if((InputPassword.length() > 6  )&&(Patterns.EMAIL_ADDRESS.matcher(InputEmail).matches()) ){
                ButtonLogin.setEnabled(true);
            }
                else {
                ButtonLogin.setEnabled(false);
            }


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
