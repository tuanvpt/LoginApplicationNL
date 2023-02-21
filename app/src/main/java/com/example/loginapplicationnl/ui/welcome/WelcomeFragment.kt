package com.example.loginapplicationnl.ui.welcome

import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import com.example.loginapplicationnl.base.BaseFragment
import com.example.loginapplicationnl.databinding.FragmentWelcomeBinding

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding, WelcomeViewModel>() {
    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentWelcomeBinding.inflate(inflater)

    override fun setUpView() {
        viewBinding.imageView.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}