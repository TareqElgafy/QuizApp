package com.example.zaki.quizapp;

import android.app.Activity;
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


public class Create_new_user extends Activity {
    private static String url = "http://10.0.2.2:8000/students/create/";
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    Button sign_up_bt;
    EditText username, password, confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_new_user);

        sign_up_bt = (Button) findViewById(R.id.create_bt);
        username = (EditText) findViewById(R.id.new_name_text);
        password = (EditText) findViewById(R.id.new_pass_text);
        confirm = (EditText) findViewById(R.id.confirm_pass_text);

        sign_up_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (password.getText().toString().equals(confirm.getText().toString())) {
                    params.add(new BasicNameValuePair("username", username.getText().toString()));
                    params.add(new BasicNameValuePair("password", password.getText().toString()));
                    new sign_up_method().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Password doesn't match",
                            Toast.LENGTH_LONG).show();
                }


            }
        });


    }


    private class sign_up_method extends AsyncTask<Void, Void, Void> {
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

            Toast.makeText(getApplicationContext(), "succesfully created" + jsonStr, Toast.LENGTH_LONG).show();
            username.setText("");
            password.setText("");
            confirm.setText("");
        }


    }
}
