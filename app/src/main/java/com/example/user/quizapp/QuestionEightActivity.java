package com.example.user.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionEightActivity extends AppCompatActivity {

    public String answer = "etisalat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_eight);

    }
    /**
     * metthod that checks to see if any option is choosen
     * @return
     */
    public boolean isAnyOptionChoosen() {

        EditText editWord = (EditText) findViewById(R.id.edit_answer);

        if (editWord.getText().toString().isEmpty()) {
            return false;

        } else {
            return true;
        }
    }

    public void next(View view) {

        if (isAnyOptionChoosen()) {
            //an option is choosen continue
            checkCorrectAnswer();

            //this method is called when the next button is called
            Intent intent = new Intent(this, QuestionNineActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            // no option is choosen
            showSnackbar(view, "You have to select an option", "OK");
        }
    }
    public void hint(View view) {
        //to display hint
       /* Snackbar.make(view, "school", Snackbar.LENGTH_SHORT).show();*/

        Snackbar.make(view, "No cheating", Snackbar.LENGTH_LONG)
                .setAction("Thanks", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }
    /**
     * method that increments the users current score in shared preferences
     */
    public void incrementScore(){
        //1. Create a sharedpreference object
        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        //2. get current score
        int currentScore = sharedPreferences.getInt("score", 0);
        //3. increment the score
        currentScore++;
        //4. Get the shared preference editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //5. Store the new score in shared preference
        editor.putInt("score", currentScore);
        //6 commit what you have done for the shared preference to save your score
        editor.commit();
    }
    public void showSnackbar(View view, String message, String actionString) {

        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction(actionString, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }
    public void checkCorrectAnswer(){

        EditText editWord = (EditText) findViewById(R.id.edit_answer);

        //option B is correct increase persons score
        if (editWord.getText().toString().toLowerCase().equals(answer)){
            incrementScore();
        }
    }

}
