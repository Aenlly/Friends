package com.example.friends.DAL;


import com.example.friends.DBUtil.SQLUtil;
import com.example.friends.Enitiy.User;

import java.util.List;

public class MessageDAL {

    //获取聊天信息列表
    public List<Object> getEntity() {
        String sqlCmd = "select user_phone,user_nickname,m_message,m_date from message,user where user.user_phone=message.m_userid order by m_date asc limit 100";
        return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
    }



    //更新用户表信息
    public int updateuserEntity(String user_id, String user_name, String user_pwd, String user_phone) {
        String sqlCmd = "Update User set user_name='" + user_name + "',user_pwd='" + user_pwd + "',user_phone='" + user_phone + "' where card_id='" + user_id + "'";
        return SQLUtil.executeNonQuery(sqlCmd, null);
    }

    //插入用户表信息
    public int insertEntity(String meassage) {
        String sqlCmd = "Insert into message(m_userid,m_message,m_date) values('" + User.getUser_id() + "','" + meassage + "',Now())";
        return SQLUtil.executeNonQuery(sqlCmd, null);
    }

}
