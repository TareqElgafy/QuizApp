package com.example.zaki.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    Button LoginBtn, SignUpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        LoginBtn = (Button) findViewById(R.id.LoginBtn);
        SignUpBtn = (Button) findViewById(R.id.SignUpBtn);
        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Sin = new Intent(getApplicationContext(), Create_new_user.class);
                startActivity(Sin);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent Iin = new Intent(getApplicationContext(), Instructor_log_in.class);
                startActivity(Iin);


            }


        });


    }

    /* private class MyTask extends AsyncTask <String,String,String>{

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {}



        @Override
        protected void onPostExecute(String s) {

        }
    }*/


}
