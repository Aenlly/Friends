package com.example.friends.DAL;

import com.example.friends.DBUtil.SQLUtil;
import com.example.friends.Enitiy.User;

import java.util.List;

public class FriendDAL {
    //获取用户表信息列表
    public List<Object> getEntity() {
        String sqlCmd = "select f_id,f_user_friend,user_nickname from friend,user where f_user='"+ User.getUser_id() +"' and user.user_phone=friend.f_user_friend";
        return SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
    }

    public boolean getFriend(String user_id,String friend_id){
        String sqlcmd="select count(*) from friend where f_user='"+user_id+"' and f_user_friend='"+friend_id+"'";
        int result= Integer.valueOf(SQLUtil.excuteScalar(sqlcmd,null).toString());
        if (result>0) {
            return true;
        } else {
            return false;
        }
    }

    public int insertFriend(String user_id,String friend_id){
        String sqlcmd="insert into friend(F_user_friend,f_user) values ('"+friend_id+"','"+user_id+"')";
        return SQLUtil.executeNonQuery(sqlcmd,null);
    }

    //删除用户表信息
    public int deleteFriend(String user_id,String friend_id) {
        String sqlCmd = "delete from friend where f_user='" + user_id + "' and F_user_friend='"+friend_id+"'";
        return SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
    }
}
