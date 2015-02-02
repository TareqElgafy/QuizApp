package com.example.zaki.quizapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class takequiz extends Activity {

    private static final String TAG_QUESTIONS = "questions";
    private static final String TAG_QUESTION = "question";
    private static final String TAG_ANSWER = "answer";
    private static final String TAG_OPTS = "options";
    private static final String TAG_OPTS_OP1 = "op1";
    private static final String TAG_OPTS_OP2 = "op2";
    private static final String TAG_OPTS_OP3 = "op3";

    private ProgressDialog pDialog;
    private static String url = "http://myquizapp.net63.net/download";
    JSONArray questions = null;
   public  List<singleQuestion > quiz = new ArrayList<singleQuestion>();


    Button bt;
    TextView tv;
    RadioGroup rg;
    RadioButton r1, r2, r3;
    public static int correct;
    int position = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.takequiz);

        tv = (TextView) findViewById(R.id.question);
        bt = (Button) findViewById(R.id.movebt);
        rg = (RadioGroup) findViewById(R.id.rg);
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        r3 = (RadioButton) findViewById(R.id.r3);

        new GetQuestions().execute();

    }

    private class GetQuestions extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(takequiz.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            List<NameValuePair> params = new ArrayList<NameValuePair>();


            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    questions = jsonObj.getJSONArray(TAG_QUESTIONS);

                    // looping through All Contacts
                    for (int i = 0; i < questions.length(); i++) {
                        JSONObject c = questions.getJSONObject(i);

                        String question = c.getString(TAG_QUESTION);
                        String answer = c.getString(TAG_ANSWER);

                        // opts node is JSON Object
                        JSONObject opts = c.getJSONObject(TAG_OPTS);
                        String op1 = opts.getString(TAG_OPTS_OP1);
                        String op2 = opts.getString(TAG_OPTS_OP2);
                        String op3 = opts.getString(TAG_OPTS_OP3);

                        // tmp hashmap for single contact

                        singleQuestion sQ = new singleQuestion();
                        sQ.question = question;
                        sQ.answer = answer;
                        sQ.opts[0] = op1;
                        sQ.opts[1] = op2;
                        sQ.opts[2] = op3;

                        quiz.add(sQ);

                        // adding contact to contact list

                        //     contactList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }


            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    tv.setText(quiz.get(position).question);
                    r1.setText( quiz.get(position).opts[0]);
                    r2.setText(quiz.get(position).opts[1]);
                    r3.setText(quiz.get(position).opts[2]);


                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            RadioButton selectedans = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                            String selectedansText = selectedans.getText().toString();

                            if (selectedansText == quiz.get(position).answer) {
                                correct++;
                            }

                            position++;

                            if (position < quiz.size()) {

                                tv.setText(quiz.get(position).question);
                                r1.setText( quiz.get(position).opts[0]);
                                r2.setText(quiz.get(position).opts[1]);
                                r3.setText(quiz.get(position).opts[2]);

                            } else {

                                Intent in = new Intent(getApplicationContext(),result.class);
                                 startActivity(in);

                            }

                        }

                    });
                }
            });


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
