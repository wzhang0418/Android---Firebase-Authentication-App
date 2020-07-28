package com.apolis.firebaseauthdemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apolis.firebaseauthdemo.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        button_register.setOnClickListener {
            var email = edit_text_email_register.text.toString()
            var password = edit_text_password_register.text.toString()

            var auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful)
                        Toast.makeText(applicationContext, "registered", Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(applicationContext, "register failed", Toast.LENGTH_LONG)
                            .show()
                }
// can also write this way:
//            var auth = FirebaseAuth.getInstance()
//            auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, object: OnCompleteListener<AuthResult>{
//                    override fun onComplete(task: Task<AuthResult>){
//                        if(task.isSuccessful){
//                            Toast.makeText(applicationContext, "registered", Toast.LENGTH_LONG).show()
//                        }else{
//                            Toast.makeText(applicationContext, "register failed", Toast.LENGTH_LONG).show()
//                        }
//                    }
//                })
        }

        text_view_already_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        text_view_forgot_password_register.setOnClickListener {
            startActivity((Intent(this, ForgotPasswordActivity::class.java)))
        }
    }
}
