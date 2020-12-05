package com.example.friends.DAL;


import com.example.friends.DBUtil.SQLUtil;
import com.example.friends.Enitiy.User;

public class LoginDAL {

    //检测用户登录信息是否合法，合法这返回true
    public boolean checkLogin(String user_id, String user_pwd) {
        String sqlCmd = "";
        sqlCmd = "select count(*) from user where user_phone=? and user_pwd=?";//要执行的查询T-SQL命令
        Object[] objList = new Object[2];//对象数组，用来作为？参数的容器
        objList[0] = user_id;
        objList[1] = user_pwd;
        String result = SQLUtil.excuteScalar(sqlCmd, objList).toString();//执行带参数查询
        if (result.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    //根据用户Id获取用户名
    public String getName(String user_id) {
        String sqlCmd = "";
        sqlCmd = "select user_nickname from user where user_phone='" + user_id + "'";
        String result = SQLUtil.excuteScalar(sqlCmd, null).toString();
        return result;
    }

    //判断账号信息是否正确
    public boolean Bool(String user_phone){
        String sqlCmd = "select count(*) from user where user_phone='" + user_phone + "'";
        String result = SQLUtil.excuteScalar(sqlCmd, null).toString();
        if (result.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    //初始化密码
    public boolean CheckPwd(String newpwd){
        String sqlCmd = "update user set user_pwd='"+newpwd+"' where user_phone='" + User.getUser_id() + "' and user_pwd='" + User.getPwd() + "'";
        if(SQLUtil.executeNonQuery(sqlCmd, null)==1){
            return true;
        }
        return false;
    }

    public int regies(String user_id,String user_nickname,String user_pwd){
        String sqlcmd="insert into user values('"+user_id+"','"+user_nickname+"','"+user_pwd+"')";
        return SQLUtil.executeNonQuery(sqlcmd,null);
    }
}
