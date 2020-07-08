package com.example.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardPage extends AppCompatActivity {
 private TextView welcomeUser ;
 private Button LogoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_page);

        welcomeUser=findViewById(R.id.Welcome_text);
        LogoutButton=findViewById(R.id.Logout_button);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("userInfo",0);
        String firstname = sharedPreferences.getString("firstnameKey","");
        String lastname = sharedPreferences.getString("lastnameKey","");
        welcomeUser.setText("Welcome" + " " +firstname+ " "  +lastname);




        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(DashboardPage.this,MainActivity.class);
                  startActivity(intent);


                            }
        });





    }
}
