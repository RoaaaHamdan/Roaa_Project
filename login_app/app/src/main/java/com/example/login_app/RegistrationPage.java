package com.example.login_app;

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
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class RegistrationPage extends AppCompatActivity {
      private EditText FirstNameText;
      private EditText  LastNameText;
      private EditText EmailText;
      private EditText PasswordText;
      private EditText VerfieyPasswordText;
      private Button SignUpButton;
      private TextView ErrorText;
      private TextView backLogin;
      private TextView Strengthpass;
      SharedPreferences sharedPreferences;
      static final String FirstName = "firstnameKey";
      static final String LastName = "lastnameKey";
      static final String Email = "emailKey";
      static final String pass = "passKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        FirstNameText = findViewById(R.id.FirstNameText);
        LastNameText=findViewById(R.id.LastNameText);
        EmailText=findViewById(R.id.EmailText);
        PasswordText=findViewById(R.id.PasswordText);
        VerfieyPasswordText=findViewById(R.id.VerfieyPasswordText);
        SignUpButton=findViewById(R.id.SignUpButton);
        ErrorText = findViewById(R.id.error_text);
        Strengthpass=findViewById(R.id.Strengthpass);
        backLogin=findViewById(R.id.back_login);
        FirstNameText.addTextChangedListener(SignUPWatcher);
        LastNameText.addTextChangedListener(SignUPWatcher);
        PasswordText.addTextChangedListener(SignUPWatcher);
        VerfieyPasswordText.addTextChangedListener(SignUPWatcher);
        EmailText.addTextChangedListener(SignUPWatcher);



     SignUpButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             boolean isnmuberOrspace1=false;
             boolean isnmuberOrspace2=false;
             boolean wrong_pass=false;
             String Password = PasswordText.getText().toString();
             String VerfieyPass = VerfieyPasswordText.getText().toString();
             String firstname=FirstNameText.getText().toString();
             String lastname=LastNameText.getText().toString();
             int i ;

             char First_name[] = firstname.toCharArray();
             for ( i = 0 ; i<First_name.length;i++){
                 if( Character.isDigit(First_name[i])|| (Character.isSpace(First_name[i]))){
                     isnmuberOrspace1=true;
                     break;
                 }

             }

             char Last_name[] = lastname.toCharArray();
             for ( i = 0 ; i<Last_name.length;i++){
                 if( Character.isDigit(Last_name[i]) || (Character.isSpace(Last_name[i]))){
                     isnmuberOrspace2=true;
                     break;

                 }

             }
             if ( ! Password.equals(VerfieyPass)){
                 wrong_pass=true;
             }


             if ((isnmuberOrspace1&&isnmuberOrspace2 )|| (isnmuberOrspace2&&wrong_pass)|| (isnmuberOrspace1&&wrong_pass)||(isnmuberOrspace1&&isnmuberOrspace2&&wrong_pass)){
                 ErrorText.setText("There are more than one filed with wrong format !!");
             }
             else if (isnmuberOrspace1 || isnmuberOrspace2|| wrong_pass) {


                 if (isnmuberOrspace1){
                     ErrorText.setText("First Name must be only characters!");

                 }
                 else if (isnmuberOrspace2){
                     ErrorText.setText("Last Name must be only characters!");

                 }
                 else if (wrong_pass){

                     ErrorText.setText("two passwords are not the same please try again!");
                 }
             }


             else {

                 String email=EmailText.getText().toString();
                 sharedPreferences=getSharedPreferences("userInfo",0);
                 SharedPreferences.Editor editor = sharedPreferences.edit();
                 editor.putString(FirstName,firstname);
                 editor.putString(LastName,lastname);
                 editor.putString(Email,email);
                 editor.putString(pass,Password);
                 editor.commit();


                 Intent intent = new Intent(RegistrationPage.this,MainActivity.class);
                 startActivity(intent);
             }





         }
     });




        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationPage.this,MainActivity.class );
                startActivity(intent);
            }
        });



    }



    private TextWatcher SignUPWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String FirstName = FirstNameText.getText().toString();
            String LastName = LastNameText.getText().toString();
            String Password = PasswordText.getText().toString();
            String VerfieyPass = VerfieyPasswordText.getText().toString();
            String Email = EmailText.getText().toString().trim();





            if (   (FirstName.length()>2) &&(LastName.length()>2)&& (Password.length()>6)&& (VerfieyPass.length()>6) && (Patterns.EMAIL_ADDRESS.matcher(Email).matches())){
                    SignUpButton.setEnabled(true);
            }

            else {
                SignUpButton.setEnabled(false);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
            String Password = PasswordText.getText().toString();

            int Counter =0 ;
            int CounterUpperChar=0;
            int CounterLowerChar=0;
            char pass[] = Password.toCharArray();
            for (int i = 0 ; i<pass.length;i++){
                if( Character.isDigit(pass[i]) ){
                    Counter++;

                }
                else if ( Character.isUpperCase(pass[i]) ){
                    CounterUpperChar++;

                }
                else if (Character.isLowerCase(pass[i]) ){
                    CounterLowerChar++;
                }

            }


            if(Password.length() ==0){
                Strengthpass.setText(" ");

            }
            else if (Password.length() > 8 && (Counter>0)&&(CounterLowerChar > 0)&&(CounterUpperChar > 0)){
                Strengthpass.setText("Strong Password");
            }

            else if (Password.length() > 8 && (Counter>0)&&((CounterLowerChar > 0)||(CounterUpperChar > 0))){
                Strengthpass.setText("Good Password");

            }
            else if (Password.length() < 8 &&((CounterLowerChar == 0) &&(CounterUpperChar == 0))){
                Strengthpass.setText("Weak Password");

            }




        }
    };


}
