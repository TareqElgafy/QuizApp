package com.example.zaki.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.apache.http.message.BasicNameValuePair;

/**
 * Created by Zaki on 2/2/2015.
 */
public class instructor_main extends Activity {
    Button quizbt;
    Button creategroupbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_main);
        quizbt = (Button) findViewById(R.id.quizbt);
        creategroupbt = (Button) findViewById(R.id.creategroupbt);

        quizbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(instructor_main.this, instructor_quizzes_main.class); //MainActivity is the name of current activity and HomeActivity is the name of the activity you want to start

                startActivity(intent);
            }
        });

        creategroupbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(instructor_main.this, instructor_groups_main.class); //MainActivity is the name of current activity and HomeActivity is the name of the activity you want to start

                startActivity(intent);
            }
        });

    }


}
