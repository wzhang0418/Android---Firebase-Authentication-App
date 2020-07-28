package com.apolis.firebaseauthdemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.apolis.firebaseauthdemo.R
import com.apolis.firebaseauthdemo.helpers.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        edit_text_edit.visibility = GONE
        button_action.visibility = GONE

        init()
    }

    private fun init() {
        button_change_email.setOnClickListener(this)
        button_change_password.setOnClickListener(this)
        button_sent_reset_email.setOnClickListener(this)
        button_delete_user.setOnClickListener(this)
        button_action.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        var auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        when(view.id){

            R.id.button_change_email -> {
                edit_text_edit.visibility = VISIBLE
                edit_text_edit.hint = "Enter new email address"
                button_action.visibility = VISIBLE
                button_action.text = "Change Email"

                button_action.setOnClickListener {
                    var newEmail = edit_text_edit.text.toString().toLowerCase()

                    user!!.updateEmail(newEmail)
                        .addOnCompleteListener{
                            if(it.isSuccessful)
                                this.toast("Email changed successfully")
                            else
                                this.toast(it.exception.toString())
                        }
                }

            }

            R.id.button_change_password -> {
                edit_text_edit.visibility = VISIBLE
                edit_text_edit.hint = "Enter new password"
                edit_text_edit.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                button_action.visibility = VISIBLE
                button_action.text = "Change Password"

                button_action.setOnClickListener {
                    var newPassword = edit_text_edit.text.toString()
                    user!!.updatePassword(newPassword)
                        .addOnCompleteListener{
                            if(it.isSuccessful)
                                this.toast("Password changed successfully")
                            else
                                this.toast(it.exception.toString())
                        }
                }
            }

            R.id.button_sent_reset_email -> {
                edit_text_edit.visibility = VISIBLE
                edit_text_edit.hint = "Enter email address"
                button_action.visibility = VISIBLE
                button_action.text = "Send reset password email"

                button_action.setOnClickListener {
                    var email = intent.getStringExtra("email")
                    auth.sendPasswordResetEmail(email!!).addOnCompleteListener(this){
                        if(it.isSuccessful)
                            Toast.makeText(applicationContext, "Reset password email sent successfully", Toast.LENGTH_LONG).show()
                        else
                            Toast.makeText(applicationContext, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }

            R.id.button_delete_user -> {
                edit_text_edit.visibility = GONE
                button_action.visibility = GONE

                user!!.delete()
                    .addOnCompleteListener{
                        if(it.isSuccessful)
                            this.toast("User deleted successfully")
                        else
                            this.toast(it.exception.toString())
                    }
            }

            R.id.button_sign_out -> {
                auth.signOut().also {
                    finish()
                }
            }
        }
    }

}