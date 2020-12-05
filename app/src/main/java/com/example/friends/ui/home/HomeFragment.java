package com.example.friends.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.friends.DAL.MessageDAL;
import com.example.friends.R;

import java.text.SimpleDateFormat;
import java.util.*;

public class HomeFragment extends Fragment {

  private ListView lv_chat_list;
  private EditText ed_send;
  private Button btn_send;
  private List<MessageHome> mList = new ArrayList<>();//实例化mlist集合
  private HomeListAdapter adapter;//定义适配器
  private MessageDAL messageDAL = new MessageDAL();//信息数据执行类
  private boolean istrue = true;//用于判断数据是否已经存在在mlist中
  private List<Object> list;//定义list集合存储查询返回数据

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_home, container, false);
    lv_chat_list = root.findViewById(R.id.lv_chat_list);
    ed_send = root.findViewById(R.id.ed_send);
    btn_send = root.findViewById(R.id.btn_send);
    handler.postDelayed(runnable, 10); // 间隔10毫秒
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    lv_chat_list.setDivider(null);//自定义listview显示

    //发送按钮事件
    btn_send.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            final String message = ed_send.getText().toString().trim();//获得输入值
            //判断是否为空
            if (!TextUtils.isEmpty(message)) {
              // 点击发送后清空输入框
              ed_send.setText("");
              //创建线程执行数据库插入与查询
              new Thread(
                      new Runnable() {
                        @Override
                        public void run() {
                          messageDAL.insertEntity(message);//插入消息
                          list = messageDAL.getEntity();//获得消息，存储在list集合中
                        }
                      })
                  .start();
              //判断list是否是空
              if (list != null) {
                //遍历取值判断
                for (int i = 0; i < list.size(); i++) {
                  Object[] obj = (Object[]) list.get(i);//将list的值转换为数组
                  String str = formatDate(obj[3]);//将日期型转为字符串类型
                  istrue = addtext((String) obj[0], (String) obj[1], (String) obj[2], str);//判断是否已经存在在mlist中
                  if (istrue) {//存在不执行操作
                  } else {
                    //适配器实例化，并且传递数据与界面的句柄
                    adapter = new HomeListAdapter(HomeFragment.this, mList);
                    lv_chat_list.setAdapter(adapter);//为listview设置适配器
                    adapter.notifyDataSetChanged();//刷新适配器
                    lv_chat_list.setSelection(lv_chat_list.getBottom());//将listview定位到底部
                  }
                }
              }
            }
          }
        });
  }

  //消息数据添加方法
  private boolean addtext(
      String user_phone, String user_nickname, String message, String user_date) {
    MessageHome data = new MessageHome();//实例化信息存储类
    data.setM_userid(user_phone);//存储发送者用户id
    data.setUser_nickname(user_nickname);//存储发送者用户昵称
    data.setM_message(message);//存储发送消息
    data.setM_date(user_date);//存储发送时间

    //判断是否已经存在在mlis集合中
    if (mList.contains(data)) {
      return true;//存在返回true
    } else {
      mList.add(data);//将数据添加到mlist中
      return false;//返回false
    }
  }

  //创建handler进行异步消息处理
  private Handler handler = new Handler();
  private Runnable runnable =
      new Runnable() {
        public void run() {
          this.update();//执行更新方法
          handler.postDelayed(this, 10); // 间隔10毫秒
        }

        void update() {
          //创建线程执行数据库语句
          new Thread(
                  new Runnable() {
                    @Override
                    public void run() {
                      list = messageDAL.getEntity();//获得数据并且存储在list中
                    }
                  })
              .start();
          //判断list是否是空
          if (list != null) {
            //遍历取值判断
            for (int i = 0; i < list.size(); i++) {
              Object[] obj = (Object[]) list.get(i);//将list的值转换为数组
              String str = formatDate(obj[3]);//将日期型转为字符串类型
              istrue = addtext((String) obj[0], (String) obj[1], (String) obj[2], str);//判断是否已经存在在mlist中
              if (istrue) {//存在不执行操作
              } else {
                //适配器实例化，并且传递数据与界面的句柄
                adapter = new HomeListAdapter(HomeFragment.this, mList);
                lv_chat_list.setAdapter(adapter);//为listview设置适配器
                adapter.notifyDataSetChanged();//刷新适配器
                lv_chat_list.setSelection(lv_chat_list.getBottom());//将listview定位到底部
              }
            }
          }
        }
      };

  //时间转换方法
  public static String formatDate(Object date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return format.format(date);
  }
}
