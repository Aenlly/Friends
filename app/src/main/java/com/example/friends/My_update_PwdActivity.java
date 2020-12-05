package com.example.friends;

import android.content.Intent;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.navigation.fragment.NavHostFragment;
import com.example.friends.DAL.Login;
import com.example.friends.Enitiy.User;
import com.example.friends.ui.my.my_checkpwdFragment;

public class My_update_PwdActivity extends AppCompatActivity {

    private EditText edit_my_new_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_update__pwd);
        setTitle("修改密码");
        edit_my_new_pwd = findViewById(R.id.edit_my_new_pwd);

        //取消按钮
        findViewById(R.id.btn_my_pwd_on).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_update_PwdActivity.this.finish();//释放界面内存
            }
        });

        //确认按钮
        findViewById(R.id.btn_my_pwd_yes)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(final View view) {
                                // 判断密码长度
                                if (edit_my_new_pwd.getText().length() < 6) {
                                    // 信息提示
                                    Toast.makeText(view.getContext(), "密码长度不小于6字符！", Toast.LENGTH_LONG).show();
                                } else {
                                    // 判断用户新密码是否与原密码相同
                                    if (edit_my_new_pwd.getText().toString().equals(User.getPwd())) {
                                        // 信息提示
                                        Toast.makeText(view.getContext(), "新密码与旧密码一致，请重新输入！", Toast.LENGTH_LONG).show();
                                        edit_my_new_pwd.setText(""); // 清空
                                    } else {
                                        // 创建线程执行数据库
                                        new Thread(
                                                new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        // 实例化用户操作类
                                                        Login login = new Login();
                                                        // 获得修改是否成功
                                                        boolean is_yes_no =
                                                                login.CheckPwd(edit_my_new_pwd.getText().toString());
                                                        if (is_yes_no) {
                                                            Looper.prepare(); // 创建消息队列
                                                            // 信息提示
                                                            Toast.makeText(view.getContext(), "修改成功！", Toast.LENGTH_LONG)
                                                                    .show();
                                                            Looper.loop(); // 执行
                                                        } else {
                                                            Looper.prepare(); // 创建消息队列
                                                            // 信息提示
                                                            Toast.makeText(view.getContext(), "修改失败！", Toast.LENGTH_LONG)
                                                                    .show();
                                                            Looper.loop(); // 执行消息队列
                                                        }
                                                    }
                                                })
                                                .start();
                                    }
                                }
                            }
                        });
    }
}
