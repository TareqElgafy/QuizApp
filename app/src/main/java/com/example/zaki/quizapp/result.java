package com.example.zaki.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Zaki on 1/29/2015.
 */
public class result extends Activity {

    TextView tv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result);
        tv = (TextView)findViewById(R.id.result);
        tv.setText("your final result is "+quiz.correct+"/"+quiz.questions.length);



    }
}
