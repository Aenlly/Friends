package com.example.friends.ui.dashboard;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.friends.DAL.FriendDAL;
import com.example.friends.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private ListView lv_dashboard_list;
    private final FriendDAL friendDAL = new FriendDAL();
    private DashboarAdapter adapter; // 定义适配器
    private List<Object> list; // 定义list集合
    private final List<FriendDashboard> friendlist = new ArrayList<>(); // 定义并且实例化friend集合
    // 创建handler异步消息处理
    private final Handler handler = new Handler();
    private final Runnable runnable =
            new Runnable() {
                @Override
                public void run() {
                    update(); // 执行更新
                    handler.postDelayed(this, 10); // 间隔10毫
                }

                void update() {
                    // 创建线程执行数据库操作
                    new Thread(
                            new Runnable() {
                    @Override
                    public void run() {
                      list = friendDAL.getEntity(); // 查询好友返回存储在list中
                    }
                  })
              .start();
          // 判断list是否为空
          if (list != null) {
            for (Object o : list) { // 遍历取值
              Object[] obj = (Object[]) o; // 将值存储在数组中
              // 实例化并且将值存储在好友类中
              FriendDashboard friendDashboard =
                  new FriendDashboard((String.valueOf(obj[0])), (String) obj[1], (String) obj[2]);
              if (friendlist.contains(friendDashboard)) { // 判断好友list集合是否存在该数据，存在不执行任何操作
              } else { // 否则
                  friendlist.add(friendDashboard); // 将数据存在friendlist中
                  // 实例化适配器，并且传递friendlist集合与视图的句柄
                  adapter = new DashboarAdapter(DashboardFragment.this, friendlist);
                  lv_dashboard_list.setAdapter(adapter); // 设置适配器
                  adapter.notifyDataSetChanged(); // 刷新
              }
            }
          }
                }
            };

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        lv_dashboard_list = root.findViewById(R.id.lv_dashboard_list);
        handler.postDelayed(runnable, 10); // 间隔10毫
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv_dashboard_list.setDivider(null); // 自定义listview
    }
}
