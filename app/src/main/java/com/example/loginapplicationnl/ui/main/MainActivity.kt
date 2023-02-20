package com.example.loginapplicationnl.ui.main

import android.view.LayoutInflater
import com.example.loginapplicationnl.base.BaseActivity
import com.example.loginapplicationnl.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun inflateViewBinding(inflater: LayoutInflater) =
        ActivityMainBinding.inflate(inflater)

    override fun initView() {
        // TODO
    }

    override fun initData() {
        // TODO
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }
}