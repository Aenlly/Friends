package com.example.friends.ui.dashboard;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.friends.DAL.MessageFriendDAL;
import com.example.friends.Enitiy.Friends;
import com.example.friends.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

  private final List<MessagesFriends> mlist = new ArrayList<>(); // list数据集
  private final MessageFriendDAL friendDAL = new MessageFriendDAL(); // 数据实体类
  private final Handler handler = new Handler(); // 创建handler进行异步消息处理
  boolean istrue = true; // 用于判断数据是否重复
  private ListView lv_friend_list; // listview视图
  private Button btn_friend_send; // 按钮
  private EditText ed_friend_send; // 文本框
  private FriendListAdapter adapter; // list视图适配器
  private List<Object> list; // 存储数据
  private final Runnable runnable =
          new Runnable() {
            @Override
            public void run() {
              update();
              handler.postDelayed(this, 10); // 间隔10毫秒
            }

            void update() {
              // 线程获得数据库的数据
              new Thread(
                      new Runnable() {
                        @Override
                        public void run() {
                          list = friendDAL.getEntity();
                        }
                      })
                      .start();
              if (list != null) {
                for (int i = 0; i < list.size(); i++) { // 遍历list
                  Object[] obj = (Object[]) list.get(i); // 将list的集合存在object数组中
                  String str = formatDate(obj[3]); // 将返回时间类型转换为字符串类型
                  istrue =
                          addtext((String) obj[0], (String) obj[1], (String) obj[2], str); // 传递数据判断是否数据为更改
                  if (istrue) { // 未更改数据不执行任何操作
                  } else {
                    adapter = new FriendListAdapter(FriendsActivity.this, mlist); //
                    lv_friend_list.setAdapter(adapter); // listview设置适配器
                    adapter.notifyDataSetChanged(); // 刷新窗体数据
                    lv_friend_list.setSelection(lv_friend_list.getBottom()); // 将listview的选择设置位到最底部
                  }
                }
              }
            }
          };

  // 时间转换方法
  public static String formatDate(Object date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间的格式
    return format.format(date); // 转换并且返回
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_friends);
    setTitle(Friends.getNickname()); // 获得好友的昵称，并且在标题栏设置

    lv_friend_list = findViewById(R.id.lv_friend_list);
    ed_friend_send = findViewById(R.id.ed_friend_send);
    btn_friend_send = findViewById(R.id.btn_friend_send);
    handler.postDelayed(runnable, 10); // 间隔10毫秒
    lv_friend_list.setDivider(null); // 设置listview显示自定义
    // 输入框的单击事件
    btn_friend_send.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                switch (v.getId()) {
                  case R.id.btn_friend_send:
                    final String message = ed_friend_send.getText().toString().trim(); // 获得输入框的文本
                    if (!TextUtils.isEmpty(message)) { // 判断是否为空
                      // 点击发送后清空输入框
                      ed_friend_send.setText("");
                      // 创建线程执行sql
                      new Thread(
                              new Runnable() {
                                @Override
                                public void run() {
                                  friendDAL.insertFriend(message); // 数据库插入消息
                                  list = friendDAL.getEntity(); // 获得数据库消息数据
                                }
                              })
                              .start(); // 执行
                      if (list != null) { // 返回的list如果不为空
                        for (int i = 0; i < list.size(); i++) { // 遍历
                          Object[] obj = (Object[]) list.get(i); // 将list的集合存在object数组中
                          String str = formatDate(obj[3]); // 将返回时间类型转换为字符串类型
                          istrue =
                                  addtext(
                                          (String) obj[0],
                                          (String) obj[1],
                                          (String) obj[2],
                                          str); // 传递数据判断是否数据为更改
                          if (istrue) { // 未更改数据不执行任何操作
                          } else {
                            adapter = new FriendListAdapter(FriendsActivity.this, mlist); //
                            lv_friend_list.setAdapter(adapter); // listview设置适配器
                            adapter.notifyDataSetChanged(); // 刷新窗体数据
                            lv_friend_list.setSelection(
                                    lv_friend_list.getBottom()); // 将listview的选择设置位到最底部
                          }
                        }
                      }
                    } else {
                      return;
                    }
                    break;
                }
              }
            });
  }

  // 添加消息
  private boolean addtext(String user_id, String friend_id, String message, String user_date) {
    MessagesFriends data = new MessagesFriends(); // 实例化消息类
    data.setMs_userid(user_id); // 设置发送者的is
    data.setMs_friendnickname(Friends.getNickname()); // 设置好友的昵称
    data.setMs_friendid(friend_id); // 设置好友的id
    data.setMs_message(message); // 设置消息
    data.setMs_ms_date(user_date); // 设置发送时间
    if (mlist.contains(data)) { // 判断数据是否存在
      return true; // 存在返回true
    } else {
      mlist.add(data); // 否则存进mlist集合中
      return false; // 并且返回false
    }
  }
}
