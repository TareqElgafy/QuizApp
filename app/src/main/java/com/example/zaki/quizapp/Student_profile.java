package com.example.zaki.quizapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class Student_profile extends ActionBarActivity {

    private static String url = "http://10.0.2.2:8000/students/profiles/create/";
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    Button submit_bt;
    EditText nameTxtView, emailtxtview, univTxtViwe, minorTxtView, sectionTxtview;
    Spinner Year_spinner, dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        nameTxtView = (EditText) findViewById(R.id.name_txt);
        emailtxtview = (EditText) findViewById(R.id.email_txt);
        univTxtViwe = (EditText) findViewById(R.id.univ_txt);
        minorTxtView = (EditText) findViewById(R.id.minortxt);
        sectionTxtview = (EditText) findViewById(R.id.section_txt);

        submit_bt = (Button) findViewById(R.id.btnSubmit);

        Year_spinner = (Spinner) findViewById(R.id.Yearspinner);
        String[] years = new String[]{"0", "1", "2", "3", "4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        Year_spinner.setAdapter(adapter);

        dropdown = (Spinner) findViewById(R.id.majorspinner);
        String[] items = new String[]{"0", "10", "20", "30", "40"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter2);


        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                {
//                    "name": "",
//                        "email": "",
//                        "university": "",
//                        "year": null,
//                        "major": null,
//                        "minor": "",
//                        "section": ""
//                }


                params.add(new BasicNameValuePair("name", nameTxtView.getText().toString()));
                params.add(new BasicNameValuePair("email", emailtxtview.getText().toString()));
                params.add(new BasicNameValuePair("university", univTxtViwe.getText().toString()));
                params.add(new BasicNameValuePair("year", Year_spinner.getSelectedItem().toString()));
                params.add(new BasicNameValuePair("major", dropdown.getSelectedItem().toString()));
                params.add(new BasicNameValuePair("minor", minorTxtView.getText().toString()));
                params.add(new BasicNameValuePair("section", sectionTxtview.getText().toString()));
                new AsyncTask<Void, Void, Void>() {
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
                    }
                }.execute();
            }
        });

    }


}
