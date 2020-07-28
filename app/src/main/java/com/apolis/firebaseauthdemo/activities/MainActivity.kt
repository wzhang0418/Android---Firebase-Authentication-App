package com.apolis.firebaseauthdemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apolis.firebaseauthdemo.R
import com.apolis.firebaseauthdemo.helpers.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null) {
            startActivity(Intent(this, LoginActivity::class.java))
            this.toast("You already have an account. Let's go to login page!")
        }

        init()
    }

    private fun init() {
        button_register.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        button_login.setOnClickListener {
            startActivity(Intent(this,
                LoginActivity::class.java))
        }
    }
}