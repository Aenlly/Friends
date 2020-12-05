package com.example.friends.ui.my;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.fragment.NavHostFragment;
import com.example.friends.DAL.Login;
import com.example.friends.Enitiy.User;
import com.example.friends.R;

public class my_checkpwdFragment extends Fragment {



  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_my_checkpwd, container, false);
    return root;
  }

  @Override
  public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

  }
}
