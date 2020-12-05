package com.example.friends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.friends.DAL.Login;
import com.example.friends.Enitiy.User;

public class MainActivity extends AppCompatActivity {

  EditText edit_phone, edit_pwd;
  Button btn_login, btn_reages;
  String user_phone, user_pwd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();

    // 登录按钮事件
    btn_login.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            new Thread() {
              @Override
              public void run() {
                // initView();
                user_phone = edit_phone.getText().toString(); // 获得手机号
                user_pwd = edit_pwd.getText().toString(); // 获得密码
                Login _login = new Login(); // 实例化Login对象，来至DAL包
                boolean result = _login.checkLogin(user_phone, user_pwd); // 检查登陆用户是否合法
                if (result) // 登陆正确
                {
                  String user_name = _login.getName(user_phone); // 获取用户名
                  User.setNickname(user_name); // 用户类存储昵称
                  User.setUser_id(user_phone); // 存储手机号|用户id
                  User.setPwd(user_pwd); // 存储密码
                  SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);//获得user.xml文件
                  SharedPreferences.Editor editor=sp.edit();//获得编辑器

                  editor.putString("userid",""+user_phone);//存储用户id
                  editor.putString("userpwd",""+user_pwd);//存储密码
                  editor.putString("username",user_name);//存储昵称
                  editor.commit();//提交修改
                  // 意图跳转
                  Intent intent = new Intent(MainActivity.this, bottomActivity.class);
                  startActivity(intent);
                  MainActivity.this.finish(); // 释放该界面的内存
                } else { // 登陆错误
                  Toast.makeText(MainActivity.this, "信息错误", Toast.LENGTH_LONG).show();
                }
              }
            }.start();
          }
        });

    // 注册按钮事件
    btn_reages.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            //意图跳转
            Intent intent = new Intent(MainActivity.this, riagerctivity.class);
            startActivity(intent);
          }
        });
  }

  //初始化获得控件id
  public void init() {
    edit_phone = findViewById(R.id.edit_phone);
    edit_pwd = findViewById(R.id.edit_pwd);
    btn_login = findViewById(R.id.btn_login);
    btn_reages = findViewById(R.id.btn_reages);
  }
}
