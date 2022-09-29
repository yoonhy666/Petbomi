package com.example.petbomi;

/**
 * 사용자 계정 정보 보낼 클래스
 */
public class UserAccount {


    private String IdToken;
    private String emailId;
    private String password;
    private String name;
    private String nickname;
    private String phnum;
    private String age;
    private String gender;
    private String address;
    private String address2;

    public UserAccount(){} //리얼타임데이터베이스 사용시 빈 생성자 반드시 있어야함
    public String getIdToken() {
        return IdToken;
    }

    public void setIdToken(String idToken) {
        IdToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhnum() {
        return phnum;
    }

    public void setPhnum(String phnum) {
        this.phnum = phnum;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}
