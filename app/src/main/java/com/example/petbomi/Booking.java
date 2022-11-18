package com.example.petbomi;

public class Booking {

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String option6;
    private String option7;
    private String option8;
    private String text_option;
    private String documentId;
    private String bomiId;
    private String date;
    private String sTime;
    private String fTime;
    private String bomiProfile;

    public Booking() {
    }

    public Booking(String option1, String option2, String option3, String option4, String option5, String option6, String option7, String option8, String text_option, String documentId, String bomiId, String date, String sTime, String fTime, String bomiProfile) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.option6 = option6;
        this.option7 = option7;
        this.option8 = option8;
        this.text_option = text_option;
        this.documentId = documentId;
        this.bomiId = bomiId;
        this.date = date;
        this.sTime = sTime;
        this.fTime = fTime;
        this.bomiProfile = bomiProfile;

    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public String getOption6() {
        return option6;
    }

    public void setOption6(String option6) {
        this.option6 = option6;
    }

    public String getOption7() {
        return option7;
    }

    public void setOption7(String option7) {
        this.option7 = option7;
    }

    public String getOption8() {
        return option8;
    }

    public void setOption8(String option8) {
        this.option8 = option8;
    }

    public String getText_option() {
        return text_option;
    }

    public void setText_option(String text_option) {
        this.text_option = text_option;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getBomiId() {
        return bomiId;
    }

    public void setBomiId(String bomiId) {
        this.bomiId = bomiId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String getfTime() {
        return fTime;
    }

    public void setfTime(String fTime) {
        this.fTime = fTime;
    }

    public String getBomiProfile() {
        return bomiProfile;
    }

    public void setBomiProfile(String bomiProfile) {
        this.bomiProfile = bomiProfile;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", option5='" + option5 + '\'' +
                ", option6='" + option6 + '\'' +
                ", option7='" + option7 + '\'' +
                ", option8='" + option8 + '\'' +
                ", text_option='" + text_option + '\'' +
                ", documentId='" + documentId + '\'' +
                ", bomiId='" + bomiId + '\'' +
                ", date='" + date + '\'' +
                ", sTime='" + sTime + '\'' +
                ", fTime='" + fTime + '\'' +
                ", bomiProfile='" + bomiProfile + '\'' +
                '}';
    }
}
