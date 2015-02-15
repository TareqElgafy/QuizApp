package com.example.zaki.quizapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zaki on 2/2/2015.
 */
public class create_new_quiz extends Activity {

    Button Sumbitnewquiz;
    EditText name, txtTestDuration, txtDateFrom, TxtDateTo;
    Spinner Groups;
    public static String QuizName, testDuration, DateFrom, DateTo;
    JSONObject Parent = new JSONObject();
    public List<NameValuePair> params = new ArrayList<NameValuePair>();
    public ProgressDialog pDialog;
    private static String url = "http://services.hanselandpetal.com/restful.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_new_quiz);

        name = (EditText) findViewById(R.id.QuizNameEdit);
        txtTestDuration = (EditText) findViewById(R.id.txtTestDuration);

        Groups = (Spinner) findViewById(R.id.GroupListBox);

        Sumbitnewquiz = (Button) findViewById(R.id.Sumbitnewquiz);

        Sumbitnewquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QuizName = name.getText().toString();
                testDuration = txtTestDuration.getText().toString();//u can validate if it's a valid number befour sending
                DateFrom = txtDateFrom.getText().toString(); //u can validate if it's a valid date befour sending
                DateTo = TxtDateTo.getText().toString();//u can validate if it's a valid date befour sending
//sending date of new quiz
                //not tested *******
                JSONArray quizeData = new JSONArray();
                JSONObject jsonObj = new JSONObject();
                try {
                    jsonObj.put("QuizName", QuizName);
                    jsonObj.put("testDuration", testDuration);
                    jsonObj.put("txtDateFrom", txtDateFrom);
                    jsonObj.put("TxtDateTo", TxtDateTo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                quizeData.put(jsonObj);


                params.add(new BasicNameValuePair("QuizeData", quizeData.toString()));//??

                Log.d("Value: ", "> " + params);

                new AddQize().execute();
                Intent in = new Intent(getApplicationContext(), makequiz.class);
                startActivity(in);
            }
        });


    }


    class AddQize extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(create_new_quiz.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

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
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            /**
             * Updating parsed JSON data into ListView
             * */
        }


    }
}
