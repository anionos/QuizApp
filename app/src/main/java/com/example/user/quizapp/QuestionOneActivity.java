package com.example.user.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        resetSharedPrefernces();

    }
    //this method is called when the next button is called
    public void next(View view) {

        if (isAnyOptionChoosen()) {
            //an option is choosen continue
            checkCorrectAnswer();

            Intent intent = new Intent(this, QuestionTwoActivity.class);
            startActivity(intent);
            finish();
        } else {
            // no option is choosen
            showSnackbar(view, "You have to select an optiion", "OK");
        }

    }
    //this method works when the hint button is pressed
    public void hint(View view) {
        //displays a short note
        showSnackbar(view, "No Cheating", "Thanks");

    }
    //This method is called when the hint method is clicked
    public void showSnackbar(View view, String message, String actionString) {

        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction(actionString, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }
    /**
     * metthod that checks to see if any option is choosen
     *
     * @return
     */
    public boolean isAnyOptionChoosen() {

        RadioGroup optionsRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        // Log.e("TAG", "id" + optionsRadioGroup.getCheckedRadioButtonId());
        if (optionsRadioGroup.getCheckedRadioButtonId() == -1) {

            return false;
        } else {
            return true;
        }

    }
    public void checkCorrectAnswer() {

        RadioGroup optionsRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        switch (optionsRadioGroup.getCheckedRadioButtonId()) {

            case R.id.optionC: {

                //option c is correct increase persons score
                incrementScore();
                break;
            }
            default: {
                // do nothing
            }
        }
    }

    /**
     * method that increments the users current score in shared preferences
     */
    public void incrementScore() {
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

   public void resetSharedPrefernces(){
        //1.create a sharedpreferences object
       SharedPreferences sharedPreferences = getSharedPreferences("sp",Context.MODE_PRIVATE);
       //2.get sharedpreferences editor
       SharedPreferences.Editor editor = sharedPreferences.edit();
       //3.store the new value in the shared prefernce
       editor.putInt("score",0);
       //4.commit what you have done for the shared prefernce to save your score
       editor.commit();
   }
}