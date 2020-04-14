package com.edna.quizapptsu.ui;

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

import java.lang.reflect.Array;
import java.util.Objects;

public class QAPlayFragment extends Fragment {

    private Button answerOne;
    private Button answerTwo;
    private Button answerThree;
    private Button answerFour;
    private TextView mCounter;
    private TextView mQuestion;
    private String[] arrayOfAnswers;
    private QAProfileFragment profileFragment;
    private SharedPreferences mPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qa_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        initPlay();
    }

    private void initUI() {
        answerOne = requireView().findViewById(R.id.play_quiz_answer_one);
        answerTwo = requireView().findViewById(R.id.play_quiz_answer_two);
        answerThree = requireView().findViewById(R.id.play_quiz_answer_three);
        answerFour = requireView().findViewById(R.id.play_quiz_answer_four);
        mCounter = requireView().findViewById(R.id.play_quiz_counter);
        mQuestion = requireView().findViewById(R.id.play_quiz_txt);
        arrayOfAnswers = getResources().getStringArray(R.array.q_one_answers);
        mPref = getActivity().getSharedPreferences("qaPreferences", Context.MODE_PRIVATE);
        profileFragment = new QAProfileFragment();
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

                replaceFragmentWithSlideAnim(profileFragment);
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

    private void replaceFragmentWithSlideAnim(Fragment fragment) {
        requireFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_left_animation,
                        R.anim.slide_in_right_animation,
                        R.anim.slide_in_right_animation,
                        R.anim.slide_in_left_animation)
                .replace(R.id.main_fragment_view, fragment)
                .commit();
    }


}