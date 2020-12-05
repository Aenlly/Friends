package com.example.friends.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.friends.Enitiy.Friends;
import com.example.friends.R;

import java.util.List;

public class DashboarAdapter extends BaseAdapter {

    private final DashboardFragment dashboardFragment; // 创建句柄
    private final List<FriendDashboard> list;
    private final LayoutInflater inflater;

    public DashboarAdapter(DashboardFragment dashboardFragment, List list) {
        this.dashboardFragment = dashboardFragment;
        // 获得句柄
        this.list = list; // 获得list数据
        inflater =
                (LayoutInflater)
                        dashboardFragment
                                .getContext()
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE); // 获得视图的实例
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewFriendHolder viewFriendHolder = null; // 定义
        if (view == null) { // 判断视图是否为空，加载布局
            viewFriendHolder = new ViewFriendHolder(); // 实例化
            view = inflater.inflate(R.layout.dashboard_item, null); // 获得视图布局
            viewFriendHolder.text_dashboard = view.findViewById(R.id.text_dashboard); // 获得文本id
            view.setTag(viewFriendHolder); // 存储布局
        }

        viewFriendHolder = (ViewFriendHolder) view.getTag(); // 获得布局
        final FriendDashboard friendDashboard = list.get(position); // 获得该行数据
        viewFriendHolder.text_dashboard.setText(friendDashboard.getF_nickname()); // 设置昵称

        // 设置每一行的单击事件
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Friends.setFriend(list.get(position).getF_user_friend()); // 将好友id存储在好友表中
                        Friends.setNickname(list.get(position).getF_nickname()); // 将好友昵称存储在好友表中
                        // 意图跳转
                        Intent intent = new Intent(v.getContext(), FriendsActivity.class);
                        v.getContext().startActivity(intent);
                    }
                });

        return view; // 返回视图
    }

    class ViewFriendHolder {
        private TextView text_dashboard;
    }
}
