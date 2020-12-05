package com.example.friends.ui.dashboard;

import java.util.Objects;

// 好友聊天消息类
public class MessagesFriends {
    private String ms_userid; // 发送者id
    private String ms_friendid; // 接收者好友id
    private String ms_friendnickname; // 好友昵称
    private String ms_message; // 发送的信息
    private String ms_ms_date; // 发送时间

    public String getMs_friendnickname() {
        return ms_friendnickname;
    }

    public void setMs_friendnickname(String ms_friendnickname) {
        this.ms_friendnickname = ms_friendnickname;
    }

    public String getMs_userid() {
        return ms_userid;
    }

    public void setMs_userid(String ms_userid) {
        this.ms_userid = ms_userid;
    }

    public String getMs_friendid() {
        return ms_friendid;
    }

    public void setMs_friendid(String ms_friendid) {
        this.ms_friendid = ms_friendid;
    }

    public String getMs_message() {
        return ms_message;
    }

    public void setMs_message(String ms_message) {
        this.ms_message = ms_message;
    }

    public String getMs_ms_date() {
        return ms_ms_date;
    }

    public void setMs_ms_date(String ms_ms_date) {
        this.ms_ms_date = ms_ms_date;
    }

    // 重写equals与hashcode方法不允许有重复数据
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessagesFriends that = (MessagesFriends) o;
        return ms_userid.equals(that.ms_userid)
                && ms_friendid.equals(that.ms_friendid)
                && ms_friendnickname.equals(that.ms_friendnickname)
                && ms_message.equals(that.ms_message)
                && ms_ms_date.equals(that.ms_ms_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ms_userid, ms_friendid, ms_friendnickname, ms_message, ms_ms_date);
    }
}
