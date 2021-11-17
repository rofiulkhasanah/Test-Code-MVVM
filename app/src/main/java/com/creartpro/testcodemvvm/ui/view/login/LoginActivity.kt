package com.creartpro.testcodemvvm.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.creartpro.testcodemvvm.R
import com.creartpro.testcodemvvm.data.entities.User
import com.creartpro.testcodemvvm.databinding.ActivityLoginBinding
import com.creartpro.testcodemvvm.ui.view.home.HomeActivity
import com.creartpro.testcodemvvm.ui.viewmodel.MainViewModel
import com.creartpro.testcodemvvm.utils.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: MainViewModel
    var emailTrue = false
    var passwordTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        emailFocus()
        passwordFocus()
        validationField()

//        loginForm()
    }

    fun loginForm(){
        binding.apply {
        val email = edtPassword.text
            btnLoginSubmit.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    with(binding) {
                        progressbar.visibility = View.VISIBLE
                        delay(3000)

                        progressbar.visibility = View.GONE
                        val user = User(email = email.toString())
                        viewModel.setUser(user)
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

    fun validationField(){
        if(emailValidation() == null && passwordValidation() == null){
            loginForm()
        }
    }

    fun emailFocus(): Boolean{
        binding.edtEmail.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.inputEmail.helperText = emailValidation()
                emailTrue = emailValidation() == null
            }
        }
        return emailTrue
    }

    private fun emailValidation(): String?
    {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val email = binding.edtEmail.text
        return if(!email.matches(emailPattern.toRegex())) {
            emailTrue = false
            "Format Email Salah"
        }else{
            emailTrue = true
            null
        }
    }

    private fun passwordValidation(): String?{
        val edtPassword = binding.edtPassword.text.toString()
        if(edtPassword.length < 6){
            passwordTrue = false
            return "Password minimal 6 karakter" }
        if(!edtPassword.matches(".*[A-Z].*".toRegex())){
            passwordTrue = false
            return "Harus berisi 1 huruf besar" }
        if(!edtPassword.matches(".*[a-z].*".toRegex())){
            passwordTrue = false
            return "Harus berisi 1 huruf kecil" }
        passwordTrue = true
        return null
    }



    fun passwordFocus(): Boolean{
        binding.edtPassword.setOnFocusChangeListener { _, focused ->
            if(!focused){
                with(binding){
                    binding.inputPassword.helperText = passwordValidation()

                    passwordTrue = passwordValidation() == null
                }
            }
        }
        return passwordTrue
    }
}