package com.example.friends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import com.example.friends.Enitiy.User;

import java.util.Timer;
import java.util.TimerTask;

public class WeclomeActivity extends AppCompatActivity {

  Timer timer = new Timer(); // 实例化定时器
  TimerTask timerTask =
          new TimerTask() {
            @Override
            public void run() {
              SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
              if (sp.contains("userid")) { // 判断user.xlm是否存在user_id键值对
                User.setUser_id(sp.getString("userid", "")); // 读取id，存储至user类
                User.setPwd(sp.getString("userpwd", "")); // 读取密码，存储至user类
                User.setNickname(sp.getString("username", "")); // 读取昵称，存储至user类
                // 如果存在键值对，直接跳转至主页
                Intent integer = new Intent(WeclomeActivity.this, BottomActivity.class);
                startActivity(integer);
                finish(); // 释放内存
              } else {
                // 否则跳转至登录页
                Intent integer = new Intent(WeclomeActivity.this, MainActivity.class);
                startActivity(integer);
                finish(); // 释放内存
              }
            }
          };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_weclome);
    timer.schedule(timerTask, 3000); // 延迟3秒执行
  }
}
