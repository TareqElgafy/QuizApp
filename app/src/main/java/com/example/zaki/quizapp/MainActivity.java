package com.example.zaki.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    Button Ibt,Sbt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Ibt = (Button) findViewById(R.id.Instructor);
        Sbt = (Button) findViewById(R.id.Student);
        Sbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Sin = new Intent(getApplicationContext(),student_log_in.class );
                startActivity(Sin);
            }
        });

        Ibt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        Intent Iin = new Intent(getApplicationContext(),Instructor_log_in.class );
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
