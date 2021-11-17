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
//        validationField()

        loginForm()
    }

//    1. Form email, terdapat validasi format email, jika error terdapat pesan
//    dibawahnya
//    2. Form password, terdapat validasi min 6 karakter, dan harus terdapat 1 karakter
//    uppercase dan lowercase, jika error terdapat pesan dibawahnya
//    3. Button submit, hanya aktif jika semua form memenuhi kriteria
//    4. Setelah submit, muncul loading sekitar 3 detik, kemudian menuju ke halaman
//    home
//    5. Setelah berhasil login, buka aplikasi dari splash screen langsung menuju ke halaman home

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

    private val textWatcher = object: TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }

    fun validationField(){
        binding.btnLoginSubmit.isEnabled = emailFocus() && passwordFocus()
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