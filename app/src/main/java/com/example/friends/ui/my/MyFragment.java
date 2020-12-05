package com.example.friends.ui.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.friends.Enitiy.User;
import com.example.friends.MainActivity;
import com.example.friends.My_update_PwdActivity;
import com.example.friends.R;

public class MyFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my, container, false);
        final TextView text_mynickname = root.findViewById(R.id.text_mynickname); // 获得控件id
        text_mynickname.setText(User.getNickname()); // 在用户类中取出昵称设置
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 修改密码按钮单击事件
        view.findViewById(R.id.btn_my_checkpwd)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //                NavHostFragment.findNavController(myFragment.this)
                                //
                                // .navigate(R.id.action_navigation_my_to_navigation_my_checkpwd);
                                Intent intent = new Intent(view.getContext(), My_update_PwdActivity.class);
                                startActivity(intent);
                            }
                        });

        // 退出登录按钮单击事件
        view.findViewById(R.id.btn_my_nologin)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // 获得文件
                                SharedPreferences sp =
                                        view.getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit(); // 获得编辑器
                                editor.clear(); // 清空文件
                                editor.commit(); // 提交修改
                                Intent intent = new Intent(view.getContext(), MainActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        });
    }
}
