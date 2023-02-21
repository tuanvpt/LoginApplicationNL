package com.example.loginapplicationnl.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.loginapplicationnl.R
import com.example.loginapplicationnl.databinding.FragmentLoginBinding
import com.example.loginapplicationnl.utils.LogUtil
import com.example.loginapplicationnl.utils.ViewUtils.hideKeyboard
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {
    private val presenter: LoginViewModel by inject()
    lateinit var stringEmail: String
    lateinit var stringPassword: String

    private lateinit var viewBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return viewBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAccount()
        doinits()
    }


    private fun doinits() {
        viewBinding.btnLogin.setOnClickListener() {
            hideKeyboard()
            stringEmail = viewBinding.edtEmail.text.toString().trim()
            stringPassword = viewBinding.edtPassword.text.toString().trim()
            if (!validateUserEmailorMobile() or !validateUserPassword()) {
                LogUtil.e("error")
                return@setOnClickListener
            } else {
                getLogin()
            }
        }
    }

    private fun getLogin() {
        presenter.login.observe(viewLifecycleOwner, Observer {
            presenter.loginUser(stringEmail, stringPassword)
            LogUtil.e("Thanh Cong")
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