package com.example.friends.ui.home;

import java.util.Objects;

// 聊天室消息类
public class MessageHome {

    private int m_id; // 聊天id
    private String m_userid; // 发送消息的用户id
    private String user_nickname; // 发送消息的用户昵称
    private String m_message; // 发送信息
    private String m_date; // 发送事件

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_userid() {
        return m_userid;
    }

    public void setM_userid(String m_userid) {
        this.m_userid = m_userid;
    }

    public String getM_message() {
        return m_message;
    }

    public void setM_message(String m_message) {
        this.m_message = m_message;
    }

    public String getM_date() {
        return m_date;
    }

    public void setM_date(String m_date) {
        this.m_date = m_date;
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
        MessageHome that = (MessageHome) o;
        return m_id == that.m_id
                && m_userid.equals(that.m_userid)
                && user_nickname.equals(that.user_nickname)
                && m_message.equals(that.m_message)
                && m_date.equals(that.m_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_id, m_userid, user_nickname, m_message, m_date);
    }
}
