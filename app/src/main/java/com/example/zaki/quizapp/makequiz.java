package com.example.zaki.quizapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class makequiz extends Activity {

    private static String url = "http://services.hanselandpetal.com/restful.php";
    public List<singleQuestion> quiz = new ArrayList<singleQuestion>();
    TextView Quizname;
    EditText questionEntered, op1Entered, op2Entered, op3Entered;
    Spinner choices;
    Button nextQuestion, submitQuestions;
    JSONObject Parent = new JSONObject();
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makequiz);

        Quizname = (TextView) findViewById(R.id.quizname);
        Quizname.setText(create_new_quiz.QuizName);

        questionEntered = (EditText) findViewById(R.id.questionEntered);
        op1Entered = (EditText) findViewById(R.id.op1Entered);
        op2Entered = (EditText) findViewById(R.id.op2Entered);
        op3Entered = (EditText) findViewById(R.id.op3Entered);
        choices = (Spinner) findViewById(R.id.ops);
        nextQuestion = (Button) findViewById(R.id.nextQuestion);
        submitQuestions = (Button) findViewById(R.id.submitQuestions);

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                singleQuestion sQ = new singleQuestion();
                sQ.question = questionEntered.getText().toString();
                sQ.opts[0] = op1Entered.getText().toString();
                sQ.opts[1] = op2Entered.getText().toString();
                sQ.opts[2] = op3Entered.getText().toString();


                String selectedans = choices.getSelectedItem().toString();
                switch (selectedans) {
                    case "1":
                        sQ.answer = sQ.opts[0];
                        break;
                    case "2":
                        sQ.answer = sQ.opts[1];
                        break;
                    case "3":
                        sQ.answer = sQ.opts[2];
                        break;
                }

                quiz.add(sQ);

                questionEntered.setText("");
                op1Entered.setText("");
                op2Entered.setText("");
                op3Entered.setText("");
                questionEntered.setFocusable(true);


            }
        });

        submitQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONArray questionsArray = new JSONArray();

                for (int i = 0; i < quiz.size(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    try {
                        jsonObj.put("question", quiz.get(i).question);

                        jsonObj.put("answer", quiz.get(i).answer);

                        JSONObject jsonOptsObj = new JSONObject();
                        jsonOptsObj.put("op1", quiz.get(i).opts[0]);
                        jsonOptsObj.put("op2", quiz.get(i).opts[1]);
                        jsonOptsObj.put("op3", quiz.get(i).opts[2]);

                        jsonObj.put("options", jsonOptsObj);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    questionsArray.put(jsonObj);
                }

                params.add(new BasicNameValuePair("questions", questionsArray.toString()));//??

                Log.d("Value: ", "> " + params);

                new AddQuestions().execute();

                Toast.makeText(getApplicationContext(), "The Quiz has been added successfully",
                        Toast.LENGTH_LONG).show();


            }
        });


    }

    private class AddQuestions extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(makequiz.this);
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
