package com.example.zaki.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Zaki on 2/2/2015.
 */
public class instructor_groups_main extends Activity {
    Button creategroupbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_groups_main);

        creategroupbt = (Button) findViewById(R.id.creategroupbt);
        creategroupbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(instructor_groups_main.this, CreateNewGroup.class); //MainActivity is the name of current activity and HomeActivity is the name of the activity you want to start

                startActivity(intent);
            }
        });
    }
}