package com.example.friends.ui.dashboard;

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

public class FriendListAdapter extends BaseAdapter {
  private List<MessagesFriends> mList = new ArrayList<>();//创建list集合
  private Context context;//创建视图的句柄context
  private LayoutInflater inflater;

  //定义常量,区分收发信息
  public static final int chat_left = 1;//收
  public static final int chat_right = 2;//发


  // 构造器
  public FriendListAdapter(Context context, List<MessagesFriends> mList) {
    this.context = context;//获得视图的context
    this.mList = mList;//获得list数据
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//获得视图的实例
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
    int type = getItemViewType(i);//判断是左，是右
    if(view==null){
      //加载布局
      //判断左右信息，即是收到还是发出
      switch (type){
        case chat_left:
          viewHolderleft = new ViewHolderleft();//左边view实例化
          view = inflater.inflate(R.layout.home_left_item,null);//获得视图的界面xml布局
          viewHolderleft.textView_left = view.findViewById(R.id.tv_left_text);//获得视图左边的输出文本id
          viewHolderleft.textaccount_left=view.findViewById(R.id.text_left_account);//获得视图的账号昵称文本id
          view.setTag(viewHolderleft);//lisview存储左边的视图
          break;
        case chat_right:
          viewHolderright = new ViewHolderright();//左边view实例化
          view = inflater.inflate(R.layout.home_right_item,null);//获得视图的界面xml布局
          viewHolderright.textView_right =  view.findViewById(R.id.tv_right_text);//获得视图左边的输出文本id
          viewHolderright.textaccount_right=view.findViewById(R.id.text_right_account);//获得视图的账号昵称文本id
          view.setTag(viewHolderright);//lisview存储右边边的视图
          break;
      }

    }else{
      //判断左右信息，即是收到还是发出
      switch (type){
        case chat_left:
          viewHolderleft = (FriendListAdapter.ViewHolderleft) view.getTag();//获得解析的视图
          break;
        case chat_right:
          viewHolderright = (FriendListAdapter.ViewHolderright) view.getTag();//获得视图
          break;
      }

    }

    //赋值
    final MessagesFriends data = mList.get(i);//获得本次的数据
    //判断左右信息，即是收到还是发出
    switch (type){
      case chat_left:
        viewHolderleft.textView_left.setText(data.getMs_message());//设置消息值
        viewHolderleft.textaccount_left.setText(Friends.getNickname());//设置昵称值
        break;
      case chat_right:
        viewHolderright.textView_right.setText(data.getMs_message());//设置消息值
        viewHolderright.textaccount_right.setText(User.getNickname());//设置昵称值
        break;
    }

    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (User.getUser_id().equals(data.getMs_userid())) {//判断账号是否与登录的账号相同，相同点击无反应
        } else {//否则跳转
          Friends.setFriend(data.getMs_userid());//存储账号id
          Friends.setNickname(data.getMs_friendnickname());//存储昵称
          Intent intent = new Intent(v.getContext(), AccountActivity.class);//意图跳转
          v.getContext().startActivity(intent);//跳转
        }
      }
    });

    return view;
  }

  //获取当前Item的类型
  @Override
  public int getItemViewType(int position) {
    MessagesFriends meassData= mList.get(position);
    int type = meassData.getMs_userid().equals(User.getUser_id())?2:1;//判断id是否与登录用户id相同，相同为2，不同为1
    return type;
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
  // 返回所有Layout数据
  @Override
  public int getViewTypeCount() {
    return 3;
  }
}
