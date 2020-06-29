package com.edna.quizapptsu.data;

import java.util.ArrayList;

public class QuestionModel {
    String question;
    ArrayList<String> answers;
    int rightAnswer;

    public QuestionModel(String question, ArrayList<String> answers, int rightAnswer) {
        this.question = question;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }
}
