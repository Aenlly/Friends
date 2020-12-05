package com.example.friends.Enitiy;

public class User {
    public static String user_id;
    public static String nickname;
    public static String pwd;

    public static String getUser_id() {
        return user_id;
    }

    public static void setUser_id(String user_id) {
        User.user_id = user_id;
    }

    public static String getNickname() {
        return nickname;
    }

    public static void setNickname(String nickname) {
        User.nickname = nickname;
    }

    public static String getPwd() {
        return pwd;
    }

    public static void setPwd(String pwd) {
        User.pwd = pwd;
    }
}
