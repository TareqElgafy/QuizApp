package com.example.zaki.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Zaki on 2/2/2015.
 */
public class create_new_quiz extends Activity {

    Button bt;
    EditText name , time ;
   public static String QuizName, QuizTime ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_new_quiz);

       name = (EditText) findViewById(R.id.QuizNameEdit);
        time = (EditText) findViewById(R.id.QuizTimeEdit);


        bt = (Button) findViewById(R.id.Sumbitnewquiz);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QuizName= name.getText().toString();
                QuizTime= time.getText().toString();

                Intent in = new Intent (getApplicationContext(),makequiz.class);
                startActivity(in);
            }
        });


    }
}