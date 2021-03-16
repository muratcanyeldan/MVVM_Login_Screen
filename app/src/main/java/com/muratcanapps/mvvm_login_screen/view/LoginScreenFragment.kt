package com.muratcanapps.mvvm_login_screen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.muratcanapps.mvvm_login_screen.R
import com.muratcanapps.mvvm_login_screen.databinding.FragmentLoginScreenBinding
import com.muratcanapps.mvvm_login_screen.extentions.toast
import com.muratcanapps.mvvm_login_screen.viewmodel.LoginScreenViewModel

class LoginScreenFragment : BaseFragment() {
    private var loginScreenViewModel: LoginScreenViewModel? = null
    private var binding: FragmentLoginScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginScreenViewModel = ViewModelProvider(this).get(LoginScreenViewModel::class.java)
        loginScreenViewModel!!.loginSuccessLiveData.observe(this,
            {
                if (it) {
                    context?.toast("Login Successful")
                } else {
                    context?.toast(loginScreenViewModel!!.loginErrorLiveData.value.toString())
                }
            })
        loginScreenViewModel!!.loadingLiveData.observe(this,
            {
                if (!it) {
                    binding?.loadingBar?.visibility = View.GONE
                    binding?.loginButton?.isEnabled = true
                }
            })
    }

    override fun provideFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_login_screen, container, false)
        binding = FragmentLoginScreenBinding.bind(view)
        binding?.loginButton?.setOnClickListener {
            val email = binding?.emailInputField?.text.toString()
            val password = binding?.passwordInputField?.text.toString()
            binding?.loadingBar?.visibility = View.VISIBLE
            binding?.loginButton?.isEnabled = false
            if (isConnected) {
                loginScreenViewModel?.login(email, password)
            } else {
                context?.toast("Internet Connection Problem")
            }
        }
        return view
    }
}