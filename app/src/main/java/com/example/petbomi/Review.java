package com.example.petbomi;

import com.google.firebase.firestore.ServerTimestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Review {

    private String documentId;
    private float score;
    private String comment;
    private String nickname;
    @ServerTimestamp
    private Date date;

    private Review() {

    }

    public Review(String nickname, String documentId, float score, String comment, Date date) {
        this.nickname = nickname;
        this.documentId = documentId;
        this.score = score;
        this.comment = comment;
        this.date = date;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Review{" +
                "documentId='" + documentId + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", nickname='" + nickname + '\'' +
                ", date=" + date +
                '}';
    }
}
