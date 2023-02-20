package com.example.loginapplicationnl.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.Navigation
import com.example.loginapplicationnl.R
import com.example.loginapplicationnl.base.BaseFragment
import com.example.loginapplicationnl.databinding.FragmentLoginBinding
import com.example.loginapplicationnl.utils.ViewUtils.hideKeyboard
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import java.util.regex.Pattern


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    lateinit var stringEmailorMobile: String
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getBaseViewModel()
        startKoin {
            androidContext(requireContext())
            this.modules(listOf(viewModel.retrofitModule, viewModel.signInViewModel, viewModel.retrofitModule, viewModel.signInApi))
        }
    }

    private fun loginLiveData() {
        TODO("Not yet implemented")
    }

    private fun doinits() {
        gettextwathcerlogin()
        viewBinding.btnLogin.setOnClickListener() {
            hideKeyboard()
            stringEmailorMobile = viewBinding.edtEmail.text.toString().trim()
            stringPassword = viewBinding.edtPassword.text.toString().trim()
            if (!validateUserEmailorMobile() or !validateUserPassword()) {
                showToast("error")
                return@setOnClickListener
            } else {
                getLogin(stringEmailorMobile, stringPassword)
            }
        }
    }

    private fun getLogin(email: String, password: String) {
        viewModel.signInMethod(email, password)
        viewModel.isLoading.observe(this, Observer {

        })
        viewModel.signIn.observe(this, Observer {
            viewBinding.edtEmail.setText("Login thanh cong")
        })
    }

    private fun validateUserEmailorMobile(): Boolean {
        val email: String =
            viewBinding.edtEmail.text.toString().trim()

        if (viewBinding.edtEmail.text.toString()
                .isEmpty() or !isValidEmailaddress(email)
        ) {

            //TODO set messsage validation
            // viewBinding.edtEmail.error = tverror_emailormobile_login.error
            //  tverror_emailormobile_login.visibility = View.VISIBLE

            return false
        } else {
            //TODO hint message validation
            // tverror_emailormobile_login.isEnabled = false
            // tverror_emailormobile_login.visibility = View.GONE
            //tverror_emailormobile_login.error = null
        }

        return true
    }


    private fun validateUserPassword(): Boolean {
        if (viewBinding.edtPassword.text.toString()
                .isEmpty() or !isValidPassword(viewBinding.edtPassword.text.toString())
        ) {
            //tverror_password_viewlogin.error = tverror_password_viewlogin.error
            //tverror_password_viewlogin.visibility = View.VISIBLE
            return false
        } else {
            // tverror_password_viewlogin.isEnabled = false
            // tverror_password_viewlogin.visibility = View.GONE
            // tverror_password_viewlogin.error = null
        }
        return true
    }

    private fun isValidPassword(password: String): Boolean {

        val regex = ("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$")

        val p = Pattern.compile(regex)
        val m = p.matcher(password)
        return m.matches()

    }

    private fun isValidEmailaddress(email: String): Boolean {

        val emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$"
        val pat = Pattern.compile(emailRegex)
        return pat.matcher(email).matches()

    }

    private fun gettextwathcerlogin() {
        viewBinding.edtEmail.addTextChangedListener(emailTextWatcher)
        viewBinding.edtPassword.addTextChangedListener(passwordTextWatcher)
    }

    private val passwordTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateUserPassword()
        }
    }

    private val emailTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateUserEmailorMobile()
        }
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