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
import android.widget.TextView;

import com.edna.quizapptsu.R;
import com.edna.quizapptsu.data.QuestionModel;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private SharedPreferences mPref;
    private TextView mCounter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        ifPreferences();
    }

    private void initUI(View view) {
        mPref = getContext().getSharedPreferences("qaPreferences", Context.MODE_PRIVATE);
        mCounter = view.findViewById(R.id.historyCoins);
    }

    private void ifPreferences() {
        Integer count;

        mPref = getContext().getSharedPreferences("qaPreferences", Context.MODE_PRIVATE);
        if (mPref.contains("qaAnswerCounter")) {
            count = mPref.getInt("qaAnswerCounter", 0);
            mCounter.setText(count.toString());
        } else
            mCounter.setText("0");
    }
}