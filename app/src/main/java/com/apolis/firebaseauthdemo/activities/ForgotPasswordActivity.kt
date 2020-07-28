package com.apolis.firebaseauthdemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apolis.firebaseauthdemo.R
import com.apolis.firebaseauthdemo.helpers.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        init()
    }

    private fun init() {
        button_reset_password.setOnClickListener {
            if(isValid()){
                var email = edit_text_email_forgot_password.text.toString()
                var intent = Intent(this, EditActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            }else{
                this.toast("Email doesn't match the current user")
            }
        }

        button_back_forgot_password.setOnClickListener {
            finish()
        }
    }
    private fun isValid(): Boolean{
        var auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        var registeredEmail = user?.email.toString().toLowerCase()
        val enteredEmail = edit_text_email_forgot_password.text.toString().toLowerCase()
        return enteredEmail == registeredEmail
    }
}