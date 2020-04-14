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
import android.widget.TextView;

import com.edna.quizapptsu.R;

public class QAProfileFragment extends Fragment {

    private SharedPreferences mPref;
    private Integer mCounter;
    private TextView mAnswers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qa_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAnswers = view.findViewById(R.id.profile_answers_txt);
        mCounter = 0;

        mPref = getActivity().getSharedPreferences("qaPreferences", Context.MODE_PRIVATE);
        if (mPref.contains("qaAnswerCounter")) {
            mCounter = mPref.getInt("qaAnswerCounter", 0);
            mAnswers.setText(getString(R.string.right_answers, mCounter));
        } else
            mAnswers.setText(getString(R.string.right_answers, 0));

    }
}