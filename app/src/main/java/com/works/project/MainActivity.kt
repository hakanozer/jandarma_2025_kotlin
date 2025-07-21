package com.works.project

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.works.project.utils.ApiClient
import com.works.project.utils.EApiUrl
import com.works.project.utils.Valids

class MainActivity : AppCompatActivity() {

    lateinit var l_txtEmail: EditText
    lateinit var l_txtPassword: EditText
    lateinit var l_btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        l_txtEmail = findViewById(R.id.l_txtEmail)
        l_txtPassword = findViewById(R.id.l_txtPassword)
        l_btnLogin = findViewById(R.id.l_btnLogin)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        l_btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = l_txtEmail.text.toString()
        val password = l_txtPassword.text.toString()
        val valid = Valids()
        if (!valid.emailValids(email)) {
            Toast.makeText(this, "Email format fail", Toast.LENGTH_SHORT).show()
        }else if (password.length < 5) {
            Toast.makeText(this, "Password format fail", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()

        }
    }

}