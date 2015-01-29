package com.example.zaki.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Zaki on 1/29/2015.
 */
public class quiz extends Activity {

    Button bt ;
    TextView tv ;
    RadioGroup rg ;
    RadioButton r1,r2,r3;

    public static String questions [] = {"What was your first exam?","How many years in faculty of engineering?"};
    String answers [] = {"computer organization","5"};
    String opts []= {"Control","computer organization","Data Comm","4","5","6" };

    int position=0 ;
    public static int correct ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.quiz);

        tv=(TextView)findViewById(R.id.question);
        bt=(Button)findViewById(R.id.movebt);
        rg= (RadioGroup)findViewById(R.id.rg);
        r1=(RadioButton)findViewById(R.id.r1);
        r2=(RadioButton)findViewById(R.id.r2);
        r3=(RadioButton)findViewById(R.id.r3);

        tv.setText(questions[position]);
        r1.setText(opts[position]);
        r2.setText(opts[position+1]);
        r3.setText(opts[position+2]);



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioButton selectedans = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                String selectedansText = selectedans.getText().toString();

                if (selectedansText == answers[position]) {
                    correct++;
                }

                position++;
                if (position < questions.length) {

                    tv.setText(questions[position]);
                    r1.setText(opts[position * 3]);
                    r2.setText(opts[position * 3 + 1]);
                    r3.setText(opts[position * 3 + 2]);
                } else {

                    Intent in = new Intent(getApplicationContext(),result.class);
                    startActivity(in);

                }

            }

        });


    }
}