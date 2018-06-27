package com.example.user.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
    }

    //this method is called when the next button is called
    public void next(View view) {

        if (isAnyOptionChoosen()) {
            //an option is choosen continue
            checkCorrectAnswer();

            //this method is called when the next button is called
            Intent intent = new Intent(this, QuestionThreeActivity.class);
            startActivity(intent);
            finish();
        } else {
            // no option is choosen
            showSnackbar(view, "You have to select an option", "OK");
        }
    }

    public void hint(View view) {

        Snackbar.make(view, "Ask Google", Snackbar.LENGTH_LONG)
                .setAction("Thanks", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
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
    //This method checks the correct answer
    public void checkCorrectAnswer() {

        RadioGroup optionsRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        switch (optionsRadioGroup.getCheckedRadioButtonId()) {

            case R.id.optionB: {
                incrementScore();

                break;
            }
            default: {
                // do nothing
            }

        }
    }

    public void showSnackbar(View view, String message, String actionString) {

        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction(actionString, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }
   // method that increments the users current score in shared preferences
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
}
