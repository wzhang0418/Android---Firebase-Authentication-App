package com.apolis.firebaseauthdemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apolis.firebaseauthdemo.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.button_login

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        button_login.setOnClickListener {
            var email = edit_text_email_login.text.toString()
            var password = edit_text_password_login.text.toString()
            var auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                if(it.isSuccessful)
                    Toast.makeText(applicationContext, "Login Successfully", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_LONG).show()
            }
        }

        text_view_new_user.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        text_view_forgot_password_login.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }
}