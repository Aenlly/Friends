package com.example.friends;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.friends.DAL.FriendDAL;
import com.example.friends.Enitiy.Friends;
import com.example.friends.Enitiy.User;

public class AccountActivity extends AppCompatActivity {

    private TextView text_account_nickname;
    private Button btn_account_yesno;
    private FriendDAL friendDAL = new FriendDAL();
    private boolean is_y_n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        init(); // 控件id初始化
        text_account_nickname.setText(Friends.getNickname()); // 说用户昵称
        // 创建线程获得进行sql操作，判断是否是好友
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        is_y_n = friendDAL.getFriend(User.getUser_id(), Friends.getFriend()); // 判断是否为好友
                        if (is_y_n) {
                            btn_account_yesno.setText("删除好友"); // 如果已经是好友，则设置按钮显示删除还有
                        } else {
                            btn_account_yesno.setText("添加好友"); // 如果不是，则显示添加好友
                        }
                    }
                })
                .start(); // start执行
        // 按钮事件
        btn_account_yesno.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 判断按钮的文本是否是添加好友
                        if (btn_account_yesno.getText().equals("添加好友")) {
                            // 创建线程执行好友添加操作
                            new Thread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            // 获得返回值，用于判断执行插入语句是否成功
                                            int n = friendDAL.insertFriend(User.getUser_id(), Friends.getFriend());
                                            if (n > 0) {
                                                Looper.prepare(); // 创建消息队列
                                                // 消息提示在界面显示
                                                Toast.makeText(AccountActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                                                btn_account_yesno.setText("删除好友"); // 将按钮设置位删除好友文本
                                                is_y_n = true; // 将好友判断设置为已经是好友true
                                                Looper.loop(); // 消息队列执行
                                            } else { // 执行失败
                                                Looper.prepare(); // 创建消息队列
                                                // 消息提示在界面显示
                                                Toast.makeText(AccountActivity.this, "添加失败", Toast.LENGTH_LONG).show();
                                                Looper.loop(); // 消息队列执行
                                            }
                                        }
                                    })
                                    .start();
                        } else {
                            new Thread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            // 获得返回值，用于判断执行删除语句是否成功
                                            int n = friendDAL.deleteFriend(User.getUser_id(), Friends.getFriend());
                                            if (n > 0) {
                                                Looper.prepare(); // 创建消息队列
                                                // 消息提示在界面显示
                                                Toast.makeText(AccountActivity.this, "删除成功", Toast.LENGTH_LONG).show();
                                                btn_account_yesno.setText("添加好友");
                                                is_y_n = false; // 将好友判断设置为已经是不是好友false
                                                Looper.loop(); // 消息队列执行
                                            } else {
                                                Looper.prepare(); // 创建消息队列
                                                // 消息提示在界面显示
                                                Toast.makeText(AccountActivity.this, "删除失败", Toast.LENGTH_LONG).show();
                                                Looper.loop(); // 消息队列执行
                                            }
                                        }
                                    })
                                    .start();
                        }
                    }
                });
    }

    // 初始化获得控件的id
    private void init() {
        text_account_nickname = findViewById(R.id.text_account_nickname);
        btn_account_yesno = findViewById(R.id.btn_account_yesno);
    }
}
