package com.example.user.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChooseLanguageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
        resetSharedPreferences();
    }

    /*
    *This method is called when the continue button is clicked
    */
    public void nextPage(View view) {

        if (isAnyOptionChoosen()) {
            //an option is choosen continue
            checkCorrectAnswer();

            //figure out if the user wants whipped cream flavour
            RadioButton english = findViewById(R.id.english_radiobutton);
            boolean englishChecked = english.isChecked();

            //figure out if the user wants chocolate cream flavour
            RadioButton french =  findViewById(R.id.french_radiobutton);
            boolean frenchChecked = french.isChecked();

            Intent intent = new Intent(this, AboutQuizActivity.class);
            startActivity(intent);

        }
        else {
            // no option is choosen
            showSnackbar(view, "You have to select an optiion", "OK");
        }
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

        }
    }

    public void showSnackbar(View view, String message, String actionString){

        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction(actionString, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }

    /**
     * method that resets the shared preferences for the user to 0
     */
    public void resetSharedPreferences(){
        //1. Create a sharedpreference object
        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        //2. Get the shared preference editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //3. Store the new value in shared preference
        editor.putInt("score", 0);
        //4 commit what you have done for the shared preference to save your score
        editor.commit();
    }
}