package com.ydmmocoo.androidinterview.model;

import cn.bmob.v3.BmobObject;

public class NetworkSubject extends BmobObject {

    private String question;
    private Article answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Article getAnswer() {
        return answer;
    }

    public void setAnswer(Article answer) {
        this.answer = answer;
    }
}
