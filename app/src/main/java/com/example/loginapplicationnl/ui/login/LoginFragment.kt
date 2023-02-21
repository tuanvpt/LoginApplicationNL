package com.example.loginapplicationnl.ui.login

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.Navigation
import com.example.loginapplicationnl.R
import com.example.loginapplicationnl.base.BaseFragment
import com.example.loginapplicationnl.databinding.FragmentLoginBinding
import com.example.loginapplicationnl.utils.ViewUtils.hideKeyboard
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {


    private val presenter: LoginViewModel by inject()

    lateinit var stringEmail: String
    lateinit var stringPassword: String

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentLoginBinding.inflate(inflater)

    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun setUpView() {
        createAccount()
        doinits()
    }

    private fun doinits() {
        viewBinding.btnLogin.setOnClickListener() {
            hideKeyboard()
            stringEmail = viewBinding.edtEmail.text.toString().trim()
            stringPassword = viewBinding.edtPassword.text.toString().trim()
            if (!validateUserEmailorMobile() or !validateUserPassword()) {
                showToast("error")
                return@setOnClickListener
            } else {

                getLogin()
            }
        }
    }

    private fun getLogin() {
        presenter.login.observe(this, Observer {
            presenter.loginUser(stringEmail, stringPassword)
            showToast("Thanh Cong")
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

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }
}