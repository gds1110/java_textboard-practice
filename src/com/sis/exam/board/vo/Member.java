package com.sis.exam.board.vo;

public class Member {
   private int id ;
   private String loginId ;
   private String loginPw ;
   private String name ;


    public Member(int id, String loginId, String loginPw, String name) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", loginPw='" + loginPw + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public String getLoginId() {
        return loginId;
    }

    public int getId() {
        return id;
    }
}
