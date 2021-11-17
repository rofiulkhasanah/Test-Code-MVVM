package com.creartpro.testcodemvvm.ui.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.creartpro.testcodemvvm.R
import com.creartpro.testcodemvvm.ui.view.home.HomeActivity
import com.creartpro.testcodemvvm.ui.view.login.LoginActivity
import com.creartpro.testcodemvvm.ui.viewmodel.MainViewModel
import com.creartpro.testcodemvvm.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        Handler(Looper.getMainLooper()).postDelayed({
            val email = viewModel.getLocalUser().email
            if(email != null){
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 3000)
    }
}