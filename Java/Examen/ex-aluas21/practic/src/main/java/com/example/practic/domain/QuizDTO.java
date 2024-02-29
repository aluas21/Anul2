package com.example.practic.domain;

public class QuizDTO extends Entity{
    String text;
    int Punctaj;

    public QuizDTO(int id){
        super(id);
    }

    public QuizDTO(int id, String text, int Punctaj){
        super(id);
        this.text = text;
        this.Punctaj = Punctaj;
    }

    public String getText(){
        return text;
    }

    public int getPunctaj(){
        return Punctaj;
    }

    public void setPunctaj(int Punctaj){
        this.Punctaj = Punctaj;
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "text='" + text + '\'' +
                ", Punctaj=" + Punctaj +
                ", id=" + this.getId() +
                '}';
    }

}
