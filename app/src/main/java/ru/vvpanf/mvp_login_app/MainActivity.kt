package ru.vvpanf.mvp_login_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.vvpanf.mvp_login_app.login.view.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button_start).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}