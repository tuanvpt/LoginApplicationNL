package com.example.loginapplicationnl.ui.login

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.loginapplicationnl.R
import com.example.loginapplicationnl.base.BaseFragment
import com.example.loginapplicationnl.databinding.FragmentLoginBinding
import com.example.loginapplicationnl.utils.ViewUtils.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    private val presenter: LoginViewModel by viewModel<LoginViewModel>()

    lateinit var stringEmail: String
    lateinit var stringPassword: String

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentLoginBinding.inflate(inflater)

    override fun setUpView() {
        createAccount()
        doinits()
    }

    private fun doinits() {
        getLogin()
        viewBinding.btnLogin.setOnClickListener() {
            hideKeyboard()
            stringEmail = viewBinding.edtEmail.text.toString().trim()
            stringPassword = viewBinding.edtPassword.text.toString().trim()
            if (!validateUserEmailorMobile() or !validateUserPassword()) {
                showToast("error")
                return@setOnClickListener
            } else {
                presenter.loginUser(email = stringEmail, pwd = stringPassword)
            }
        }
    }

    private fun getLogin() {
        presenter.login.observe(this, Observer {
            when (it) {
                is LoginViewModel.LoginState.Loading -> showLoading()
                is LoginViewModel.LoginState.Successful -> {
                    hideLoading()
                    findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
                }
                is LoginViewModel.LoginState.Failure -> {
                    showToast("That bai")
                    hideLoading()
                }
            }
        })
    }

    private fun validateUserEmailorMobile(): Boolean {
        if (viewBinding.edtEmail.text.toString().isEmpty()
        ) {
            viewBinding.txtEmailErrorMessage.visibility = View.VISIBLE
            return false
        } else {
            viewBinding.txtEmailErrorMessage.visibility = View.INVISIBLE
            viewBinding.txtEmailErrorMessage.error = null
        }
        return true
    }

    private fun validateUserPassword(): Boolean {
        if (viewBinding.edtPassword.text.toString().isEmpty()
        ) {
            viewBinding.txtPasswordErrorMessage.visibility = View.VISIBLE
            return false
        } else {
            viewBinding.txtPasswordErrorMessage.visibility = View.INVISIBLE
            viewBinding.txtPasswordErrorMessage.error = null
        }
        return true
    }

    private fun createAccount() {
        viewBinding.txtCreateAccount.setOnClickListener() {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

}