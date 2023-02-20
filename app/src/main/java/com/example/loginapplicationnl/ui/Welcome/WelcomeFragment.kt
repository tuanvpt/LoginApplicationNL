package com.example.loginapplicationnl.ui.Welcome

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelStoreOwner
import com.example.loginapplicationnl.base.BaseFragment
import com.example.loginapplicationnl.databinding.FragmentWelcomeBinding

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding, WelcomeViewModel>() {
    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentWelcomeBinding.inflate(inflater)

    override fun getViewModelClass(): Class<WelcomeViewModel> {
        return WelcomeViewModel::class.java
    }

    override fun setUpView() {

    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }
}