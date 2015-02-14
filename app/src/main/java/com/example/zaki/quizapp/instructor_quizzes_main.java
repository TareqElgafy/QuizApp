package com.example.zaki.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Zaki on 2/2/2015.
 */
public class instructor_quizzes_main extends Activity {
    Button creatNewQuizBtn;
    Button ViewPublishedQuizzesBtn;
    Button ViewUnpublishedQuizzesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_quizzes_main);

        creatNewQuizBtn = (Button) findViewById(R.id.creatNewQuizBtn);
        ViewPublishedQuizzesBtn = (Button) findViewById(R.id.ViewPublishedQuizzesBtn);
        ViewUnpublishedQuizzesBtn = (Button) findViewById(R.id.ViewUnpublishedQuizzesBtn);

        creatNewQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(instructor_quizzes_main.this, create_new_quiz.class); //MainActivity is the name of current activity and HomeActivity is the name of the activity you want to start

                startActivity(intent);
            }
        });

        ViewPublishedQuizzesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(instructor_quizzes_main.this, viewQizes.class); //MainActivity is the name of current activity and HomeActivity is the name of the activity you want to start

                startActivity(intent);
            }
        });
        ViewUnpublishedQuizzesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//pass a parmiter to filter if it's published or not
                Intent intent = new Intent(instructor_quizzes_main.this, viewQizes.class); //MainActivity is the name of current activity and HomeActivity is the name of the activity you want to start

                startActivity(intent);
            }
        });
    }
}


