package com.edna.quizapptsu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNavBar;
    private FloatingActionButton mFab;
    private Button answerOne;
    private Button answerTwo;
    private Button answerThree;
    private Button answerFour;
    private TextView mCounter;
    private TextView mQuestion;
    private String[] arrayOfAnswers;
    private SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initFunctional();
        initPlay();
        ifPreferences();
    }

    private void initUI() {
        mNavBar = findViewById(R.id.main_nav_bar);
        mFab = findViewById(R.id.main_fab);
        answerOne = this.findViewById(R.id.play_quiz_answer_one);
        answerTwo = this.findViewById(R.id.play_quiz_answer_two);
        answerThree = this.findViewById(R.id.play_quiz_answer_three);
        answerFour = this.findViewById(R.id.play_quiz_answer_four);
        mCounter = this.findViewById(R.id.play_quiz_counter);
        mQuestion = this.findViewById(R.id.play_quiz_txt);
        arrayOfAnswers = getResources().getStringArray(R.array.q_one_answers);
        mPref = this.getSharedPreferences("qaPreferences", Context.MODE_PRIVATE);
    }

    private void initFunctional() {
        mNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    default: throw new IllegalArgumentException("Position not valid");
                    case R.id.home_item:
                        Toast.makeText(getBaseContext(), "Home", Toast.LENGTH_SHORT).show();
                    case R.id.empty_item:
                        break;
                    case R.id.profile_item:
                        Toast.makeText(getBaseContext(), "Profile", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Play", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initPlay() {

        for (int i = 0; i < arrayOfAnswers.length; i++) {
            switch (i) {
                case 0: answerOne.setText(arrayOfAnswers[i]);
                case 1: answerTwo.setText(arrayOfAnswers[i]);
                case 2: answerThree.setText(arrayOfAnswers[i]);
                case 3: answerFour.setText(arrayOfAnswers[i]);
            }
        }

        mQuestion.setText(getString(R.string.geography_question_one));
        mCounter.setText(getString(R.string.right_answers, 0));

        answerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter.setText(getString(R.string.right_answers, 1));
                Toast.makeText(getBaseContext(), "Right Answer", Toast.LENGTH_SHORT).show();
                answerOne.setTextColor(getResources().getColor(R.color.colorAccent));

                SharedPreferences.Editor mEditor = mPref.edit();
                mEditor.putInt("qaAnswerCounter", 1);
                mEditor.apply();
            }
        });

        answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });

        answerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });

        answerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ifPreferences() {
        Integer count;

        mPref = this.getSharedPreferences("qaPreferences", Context.MODE_PRIVATE);
        if (mPref.contains("qaAnswerCounter")) {
            count = mPref.getInt("qaAnswerCounter", 0);
            mCounter.setText(getString(R.string.right_answers, count));
        } else
            mCounter.setText(getString(R.string.right_answers, 0));
    }
}
