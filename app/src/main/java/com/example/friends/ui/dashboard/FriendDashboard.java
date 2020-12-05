package com.example.friends.ui.dashboard;

import java.util.Objects;

//创建好友类
public class FriendDashboard {
    private String F_id;//编号id
    private String F_user_friend;//好友id
    private String F_nickname;//好友昵称

    public FriendDashboard(String f_id, String f_user_friend, String f_nickname) {
        F_id = f_id;
        F_user_friend = f_user_friend;
        F_nickname = f_nickname;
    }

    public String getF_nickname() {
        return F_nickname;
    }

    public void setF_nickname(String f_nickname) {
        F_nickname = f_nickname;
    }

    public String getF_id() {
        return F_id;
    }

    public void setF_id(String f_id) {
        F_id = f_id;
    }

    public String getF_user_friend() {
        return F_user_friend;
    }

    public void setF_user_friend(String f_user_friend) {
        F_user_friend = f_user_friend;
    }

    //重写equals与hashcode方法不允许有重复数据
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendDashboard that = (FriendDashboard) o;
        return Objects.equals(F_id, that.F_id) &&
                Objects.equals(F_user_friend, that.F_user_friend) &&
                Objects.equals(F_nickname, that.F_nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(F_id, F_user_friend, F_nickname);
    }
}
