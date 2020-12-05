package com.example.friends.DBUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
	private static String user;
	private static String pwd;
	private static String url;
	private static String driver;
	static{
		try {
			user="root";//获取配置文件中的user对应的值
			pwd="root";//获取配置文件中的pwd对应的值
			url="jdbc:mysql://10.0.2.2:3306/make_friends?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false";//获取配置文件中的url对应的值
			driver="com.mysql.jdbc.Driver";//获取配置文件中的driver对应的值
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection()
	{
		Connection conn=null;
		try {
			Class.forName(driver);//数据库驱动注册
			conn= DriverManager.getConnection(url,user,pwd);//获取数据库链接对象
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
