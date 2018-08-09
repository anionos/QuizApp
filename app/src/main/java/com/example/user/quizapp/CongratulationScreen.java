package com.example.user.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CongratulationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrat_screen);

        int totalScore = getUserTotalScore();

        //displays user score
        Toast.makeText(CongratulationScreen.this, "Score is: "+totalScore, Toast.LENGTH_LONG).show();

        ImageView failedImage = findViewById(R.id.congrat);

        TextView awesomeTextView = findViewById(R.id.awesome_tv);

        if (totalScore < 6){
            failedImage.setImageResource(R.drawable.sorry);

            awesomeTextView.setText("Awwnn");
        }
    }
    //this method is called when the next button is called
    public void replay (View view) {

        resetSharedPreferences();

        Intent intent = new Intent(this, AboutQuizActivity.class);
        startActivity(intent);
    }
    /**
     * method that increments the users current score in shared preferences
     */
    public int getUserTotalScore(){
        //1. Create a shared preference object
        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        //2. get total score
        int totalScore = sharedPreferences.getInt("score", 0);

        return totalScore;
    }
    public void resetSharedPreferences(){
        //1. create a sharedpreference object
        SharedPreferences sharedPreferences = getSharedPreferences("sp",Context.MODE_PRIVATE);
        //2.get the sharedPreferences editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //3. Store the new value in shared preference
        editor.putInt("score", 0);
        //4 commit what you have done for the shared preference to save your score
        editor.commit();

    }
}
