package com.example.friends.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.friends.AccountActivity;
import com.example.friends.Enitiy.Friends;
import com.example.friends.Enitiy.User;
import com.example.friends.R;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends BaseAdapter {
  // 定义常量,区分收发信息
  public static final int chat_left = 1; // 收
  public static final int chat_right = 2; // 发
  private final HomeFragment homeFragment;
  private final LayoutInflater inflater;
  private List<MessageHome> mList = new ArrayList<>();

  // 构造器
  public HomeListAdapter(HomeFragment homeFragment, List<MessageHome> mList) {
    this.homeFragment = homeFragment; // 获得视图的context
    this.mList = mList; // 获得list数据
    inflater =
            (LayoutInflater)
                    homeFragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE); // 获得视图的实例
  }

  @Override
  public int getCount() {
    return mList.size();
  }

  @Override
  public Object getItem(int position) {
    return mList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(final int i, View view, ViewGroup viewGroup) {
    ViewHolderleft viewHolderleft = null;
    ViewHolderright viewHolderright = null;
    int type = getItemViewType(i); // 获得返回值判断是左是右
    if (view == null) {
      // 加载布局
      // 判断左右信息，即是收到还是发出
      switch (type) {
        case chat_left:
          viewHolderleft = new ViewHolderleft(); // 左边view实例化
          view = inflater.inflate(R.layout.home_left_item, null); // 获得视图的界面xml布局
          viewHolderleft.textView_left = view.findViewById(R.id.tv_left_text); // 获得视图左边的输出文本id
          viewHolderleft.textaccount_left =
                  view.findViewById(R.id.text_left_account); // 获得视图的账号昵称文本id
          view.setTag(viewHolderleft); // lisview存储左边的视图
          break;
        case chat_right:
          viewHolderright = new ViewHolderright(); // 左边view实例化
          view = inflater.inflate(R.layout.home_right_item, null); // 获得视图的界面xml布局
          viewHolderright.textView_right = view.findViewById(R.id.tv_right_text); // 获得视图左边的输出文本id
          viewHolderright.textaccount_right =
                  view.findViewById(R.id.text_right_account); // 获得视图的账号昵称文本id
          view.setTag(viewHolderright); // lisview存储右边边的视图
          break;
      }

    } else {
      // 判断左右信息，即是收到还是发出
      switch (type) {
        case chat_left:
          viewHolderleft = (ViewHolderleft) view.getTag(); // 获得解析的视图
          break;
        case chat_right:
          viewHolderright = (ViewHolderright) view.getTag(); // 获得解析的视图
          break;
      }
    }

    // 赋值
    final MessageHome data = mList.get(i);
    // 判断左右信息，即是收到还是发出
    switch (type) {
      case chat_left:
        viewHolderleft.textView_left.setText(data.getM_message()); // 设置消息值
        viewHolderleft.textaccount_left.setText(data.getUser_nickname()); // 设置昵称值
        break;
      case chat_right:
        viewHolderright.textView_right.setText(data.getM_message()); // 设置消息值
        viewHolderright.textaccount_right.setText(data.getUser_nickname()); // 设置昵称值
        break;
    }

    // 信息单击事件
    view.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (User.getUser_id().equals(data.getM_userid())) { // 判断账号是否与登录的账号相同，相同点击无反应
            } else { // 否则跳转
              Friends.setFriend(data.getM_userid()); // 存储用户id
              Friends.setNickname(data.getUser_nickname()); // 存储用户昵称
              // 意图跳转
              Intent intent = new Intent(v.getContext(), AccountActivity.class);
              v.getContext().startActivity(intent);
            }
          }
        });

    return view;
  }

  // 获取当前Item的类型
  @Override
  public int getItemViewType(int position) {
    MessageHome chatData = mList.get(position);
    int type =
            chatData.getM_userid().equals(User.getUser_id()) ? 2 : 1; // 判断id是否与登录用户id相同，相同为2，不同为1
    return type;
  }

  // 返回所有Layout数据
  @Override
  public int getViewTypeCount() {
    return 3;
  }

  // 左边消息控件缓存
  class ViewHolderleft {
    private TextView textView_left;
    private TextView textaccount_left;
  }

  // 右边消息控件缓存
  class ViewHolderright {
    private TextView textView_right;
    private TextView textaccount_right;
  }
}
