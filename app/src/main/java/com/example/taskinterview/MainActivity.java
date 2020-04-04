package com.example.taskinterview;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.taskinterview.databinding.ActivityMainBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;

import viewmodel.MainViewModel;


public class MainActivity extends YouTubeBaseActivity {
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = new MainViewModel(MainActivity.this, mainBinding);
        mainViewModel.load();
        mainBinding.setCheck(mainViewModel);
        mainBinding.executePendingBindings();

    }
}
