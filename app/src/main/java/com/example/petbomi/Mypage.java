package com.example.petbomi;

public class Mypage {

    private String nickname;
    private String email;

    public Mypage() {
    }

    public Mypage(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
