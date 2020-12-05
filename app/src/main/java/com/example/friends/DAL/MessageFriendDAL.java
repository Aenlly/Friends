package com.example.friends.DAL;

import com.example.friends.DBUtil.SQLUtil;
import com.example.friends.Enitiy.Friends;
import com.example.friends.Enitiy.User;

import java.util.List;

public class MessageFriendDAL {
    //获取聊天信息列表
    public List<Object> getEntity() {
    String sqlCmd =
        "select ms_userid,ms_friendid,ms_message,ms_date from messages where (ms_friendid='"
            + Friends.getFriend()
            + "' and ms_userid='"
            + User.getUser_id()
            + "') or ms_userid='"+ Friends.getFriend() +"' and ms_friendid='"+ User.getUser_id() +"'  order by ms_date asc limit 100";
        return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
    }

    public int insertFriend(String message){
        String sqlcmd="insert into messages(ms_userid,ms_friendid,ms_message,ms_date) values('"+User.getUser_id()+"','"+Friends.getFriend()+"','"+message+"',Now())";
        return SQLUtil.executeNonQuery(sqlcmd,null);
    }
}
