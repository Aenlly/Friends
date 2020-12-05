package com.example.friends;

import android.content.Intent;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.friends.DAL.Login;

import java.util.logging.Logger;

//注册界面
public class riagerctivity extends AppCompatActivity {

    private EditText edit_reages_nickname,edit_reages_phone,edit_reages_pwd;
    private Button btn_reages_yes,btn_reages_no;
    private final Login login=new Login();//实例化数据库操作类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riagerctivity);
        setTitle("注册");//设置标题栏

        init();//初始化获得控件id

        btn_reages_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断所有的输入框中任意一个都不能为空
                if(edit_reages_pwd.getText()==null||edit_reages_phone.getText()==null||edit_reages_nickname.getText()==null||edit_reages_nickname.getText().equals("")||edit_reages_phone.getText().equals("")||edit_reages_pwd.equals("")){
                    //错误消息提示
                    Toast.makeText(riagerctivity.this,"注册数据不能为空",Toast.LENGTH_LONG).show();
                }
                else if(edit_reages_phone.getText().length()<11){//判断手机号长度
                    //错误消息提示
                    Toast.makeText(riagerctivity.this,"手机号错误",Toast.LENGTH_LONG).show();
                }else if(edit_reages_pwd.getText().length()<6){//判断密码长度
                    //错误消息提示
                    Toast.makeText(riagerctivity.this,"密码长度最低为6位",Toast.LENGTH_LONG).show();
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //判断账号是否已经存在
                            if(login.Bool(edit_reages_phone.getText().toString())){
                                Looper.prepare();//创建消息队列
                                //界面提示信息
                                Toast.makeText(riagerctivity.this,"账号已存在",Toast.LENGTH_LONG).show();
                                Looper.loop();//执行消息队列
                            }else{
                                //进行数据插入
                                int i=login.regies(edit_reages_phone.getText().toString(),edit_reages_nickname.getText().toString(),edit_reages_pwd.getText().toString());
                                if(i>0){//判断是否注册成功
                                    Looper.prepare();//创建消息队列
                                    //界面提示信息
                                    Toast.makeText(riagerctivity.this,"注册成功",Toast.LENGTH_LONG).show();
                                    Looper.loop();//执行消息队列
                                }else{
                                    Looper.prepare();//创建消息队列
                                    //界面提示信息
                                    Toast.makeText(riagerctivity.this,"注册失败",Toast.LENGTH_LONG).show();
                                    Looper.loop();//执行消息队列
                                }
                            }
                        }
                    }).start();
                }
            }
        });

        //取消注册按钮事件
        btn_reages_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(riagerctivity.this,MainActivity.class);//意图跳转到登录界面
                startActivity(intent);//跳转
                riagerctivity.this.finish();//释放该界面内存
            }
        });
    }

    //初始化获得控件id
    public void init(){
        edit_reages_nickname=findViewById(R.id.edit_reags_nickname);
        edit_reages_phone=findViewById(R.id.edit_reags_phone);
        edit_reages_pwd=findViewById(R.id.edit_reags_pwd);
        btn_reages_yes=findViewById(R.id.btn_reages_yes);
        btn_reages_no=findViewById(R.id.btn_reages_no);
    }
}
