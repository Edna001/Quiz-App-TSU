package com.edna.quizapptsu.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edna.quizapptsu.R;

public class PlayFragment extends Fragment {

    private Button answerOne;
    private Button answerTwo;
    private Button answerThree;
    private Button answerFour;
    private TextView mCounter;
    private TextView mQuestion;
    private String[] arrayOfAnswers;
    private SharedPreferences mPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        initPlay();
        ifPreferences();
    }

    private void initUI(View view) {
        answerOne = view.findViewById(R.id.play_quiz_answer_one);
        answerTwo = view.findViewById(R.id.play_quiz_answer_two);
        answerThree = view.findViewById(R.id.play_quiz_answer_three);
        answerFour = view.findViewById(R.id.play_quiz_answer_four);
        mCounter = view.findViewById(R.id.play_quiz_counter);
        mQuestion = view.findViewById(R.id.play_quiz_txt);
        arrayOfAnswers = getResources().getStringArray(R.array.q_one_answers);
        mPref = getContext().getSharedPreferences("qaPreferences", Context.MODE_PRIVATE);
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
                Toast.makeText(getContext(), "Right Answer", Toast.LENGTH_SHORT).show();
                answerOne.setTextColor(getResources().getColor(R.color.colorAccent));

                SharedPreferences.Editor mEditor = mPref.edit();
                mEditor.putInt("qaAnswerCounter", 1);
                mEditor.apply();
            }
        });

        answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });

        answerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });

        answerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ifPreferences() {
        Integer count;

        mPref = getContext().getSharedPreferences("qaPreferences", Context.MODE_PRIVATE);
        if (mPref.contains("qaAnswerCounter")) {
            count = mPref.getInt("qaAnswerCounter", 0);
            mCounter.setText(getString(R.string.right_answers, count));
        } else
            mCounter.setText(getString(R.string.right_answers, 0));
    }
}