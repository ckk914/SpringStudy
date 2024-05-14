package com.study.SpringStudy.webservlet.entity;

public class Member {
    private String account;
    private String password;
    private String userName;

    public Member(String account, String password, String userName) {
        this.account = account;
        this.password = password;
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
