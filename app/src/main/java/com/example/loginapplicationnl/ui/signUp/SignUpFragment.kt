package com.example.loginapplicationnl.ui.signUp

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelStoreOwner
import com.example.loginapplicationnl.base.BaseFragment
import com.example.loginapplicationnl.databinding.FragmentSignUpBinding


class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {
    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentSignUpBinding.inflate(inflater)

    override fun getViewModelClass(): Class<SignUpViewModel> {
        return SignUpViewModel::class.java
    }

    override fun setUpView() {
        signIn()
    }

    private fun signIn() {
        viewBinding.txtSignIn.setOnClickListener() {
            requireActivity().onBackPressed()
        }
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }
}