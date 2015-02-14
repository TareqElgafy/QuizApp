package com.example.zaki.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zaki on 1/30/2015.
 */
public class Instructor_log_in extends Activity {

    List<NameValuePair> params = new ArrayList<NameValuePair>();
    private static String url = "http://10.0.2.2:8000/login";

    Button login_bt;
    EditText username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.instructor_log_in);

        login_bt = (Button) findViewById(R.id.studentlogbt);
        username = (EditText) findViewById(R.id.instr_user_name_text);
        password = (EditText) findViewById(R.id.instr_pass_text);

        username.setFocusable(true);
        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                params.add(new BasicNameValuePair("username", username.getText().toString()));
                params.add(new BasicNameValuePair("password", password.getText().toString()));
                new login_method().execute();
            }
        });
    }


    private class login_method extends AsyncTask<Void, Void, Void> {
        String jsonStr;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, params);

            Log.d("Response: ", "> " + jsonStr);

            // Log.d("myTrial: ", params.toString());
            //  String test = sh.makeServiceCall("http://10.0.2.2:8000/logout", ServiceHandler.POST, new ArrayList<NameValuePair>(){});
            //Log.d("Response: ", test);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (jsonStr.contains("Success")) {
                Intent intent = new Intent(Instructor_log_in.this, instructor_main.class); //MainActivity is the name of current activity and HomeActivity is the name of the activity you want to start

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "wrong userName or password try again",
                        Toast.LENGTH_LONG).show();
                username.setText("");
                password.setText("");
                username.setFocusable(true);
            }
        }


    }


}





