package com.example.zaki.quizapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zaki on 1/30/2015.
 */
public class Instructor_log_in extends Activity {

    List<NameValuePair> params = new ArrayList<NameValuePair>();
    private static String url = "http://10.0.2.2:8000/users/login";
    //192.168.43.1
    Button login_bt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.instructor_log_in);

        login_bt = (Button) findViewById(R.id.instrlog);
        Log.d("button: ", login_bt.toString());
        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                params.add(new BasicNameValuePair("username", "amr"));
                params.add(new BasicNameValuePair("password", "123"));


                new login_method().execute();
            }
        });
    }


    private class login_method extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, params);

            Log.d("Response: ", "> " + jsonStr);

            //  Log.d("myTrial: ", "> " + params);


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }


    }


}





