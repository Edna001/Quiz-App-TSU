package com.edna.quizapptsu.view;

import android.animation.LayoutTransition;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.edna.quizapptsu.R;
import com.edna.quizapptsu.adapter.QuestionsAdapter;
import com.edna.quizapptsu.data.QuestionModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class QuestionsFragment extends Fragment {

    private QuestionModel questionModel;
    private RecyclerView questions;
    private ImageButton addQuestion;
    private ConstraintLayout rootView;
    private LinearLayout inputs;
    private EditText questionInput;
    private EditText answerOne;
    private EditText answerTwo;
    private EditText answerThree;
    private EditText answerFour;
    private EditText answerRight;
    private MaterialButton add;
    private ArrayList<QuestionModel> arrayListOfQuestions = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);

    }

    private void initUI(View view) {
        questions = view.findViewById(R.id.questionsView);
        addQuestion = view.findViewById(R.id.questionsAddQuestion);
        rootView = view.findViewById(R.id.questionsRoot);
        inputs = view.findViewById(R.id.questionsInputs);
        questionInput = view.findViewById(R.id.questionsQuestion);
        answerOne = view.findViewById(R.id.questionsAnswerOne);
        answerTwo = view.findViewById(R.id.questionsAnswerTwo);
        answerThree = view.findViewById(R.id.questionsAnswerThree);
        answerFour = view.findViewById(R.id.questionsAnswerFour);
        answerRight = view.findViewById(R.id.questionsRightAnswer);
        add = view.findViewById(R.id.questionsAdd);

        rootView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        //question 1
        final ArrayList<String> answers = new ArrayList<>();
        answers.add("1. Moscow");
        answers.add("2. Kiev");
        answers.add("3. London");
        answers.add("4. Tokyo");
        questionModel = new QuestionModel("What is the capital city of Ukraine?", answers, 2);

        arrayListOfQuestions.add(questionModel);

        //question 2
        answers.clear();
        answers.add("1. 2009");
        answers.add("2. 2010");
        answers.add("3. 2016");
        answers.add("4. 2017");
        questionModel = new QuestionModel("In which year was the popular video game Fortnite first released?", answers, 4);

        arrayListOfQuestions.add(questionModel);

        //question 3

        answers.clear();
        answers.add("1. Volga");
        answers.add("2. Danube");
        answers.add("3. Loire");
        answers.add("4. Rhine");
        questionModel = new QuestionModel("What is the longest river in Europe?", answers, 1);

        arrayListOfQuestions.add(questionModel);

        questions.setLayoutManager(new LinearLayoutManager(getContext()));
        questions.setAdapter(new QuestionsAdapter(arrayListOfQuestions));

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputs.setVisibility(View.VISIBLE);
                addQuestion.setVisibility(View.GONE);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionInput.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Please enter question", Toast.LENGTH_SHORT).show();
                else if (answerOne.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Please enter first answer", Toast.LENGTH_SHORT).show();
                else if (answerTwo.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Please enter second answer", Toast.LENGTH_SHORT).show();
                else if (answerThree.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Please enter third answer", Toast.LENGTH_SHORT).show();
                else if (answerFour.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Please enter fourth answer", Toast.LENGTH_SHORT).show();
                else if (answerRight.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Please enter right answer (1,2,3 or 4)", Toast.LENGTH_SHORT).show();
                else {
                    answers.clear();
                    answers.add(answerOne.getText().toString());
                    answers.add(answerTwo.getText().toString());
                    answers.add(answerThree.getText().toString());
                    answers.add(answerFour.getText().toString());
                    questionModel = new QuestionModel(questionInput.getText().toString(), answers, Integer.parseInt(answerRight.getText().toString()));

                    arrayListOfQuestions.add(questionModel);
                    questions.getAdapter().notifyDataSetChanged();
                }
            }
        });
    }
}