package com.muratcanapps.mvvm_login_screen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.muratcanapps.mvvm_login_screen.R
import com.muratcanapps.mvvm_login_screen.databinding.FragmentLoginScreenBinding
import com.muratcanapps.mvvm_login_screen.viewmodel.LoginScreenViewModel

class LoginScreenFragment : Fragment() {
    private var loginScreenViewModel: LoginScreenViewModel? = null
    private var binding: FragmentLoginScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginScreenViewModel = ViewModelProvider(this).get(LoginScreenViewModel::class.java)
        loginScreenViewModel!!.loginSuccessLiveData.observe(this,
            Observer {
                context?.let { it1 -> loginScreenViewModel?.showMessage(it1, it) }
            })
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_login_screen, container, false)
        binding = FragmentLoginScreenBinding.bind(view)
        binding?.loginButton?.setOnClickListener {
            val email = binding?.emailInputField?.text.toString()
            val password = binding?.passwordInputField?.text.toString()
            loginScreenViewModel?.login(context!!, email, password)
        }
        return view
    }
}