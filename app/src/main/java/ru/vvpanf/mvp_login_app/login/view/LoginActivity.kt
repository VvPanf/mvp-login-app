package ru.vvpanf.mvp_login_app.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import ru.vvpanf.mvp_login_app.R
import ru.vvpanf.mvp_login_app.login.presenter.ILoginPresenter
import ru.vvpanf.mvp_login_app.login.presenter.LoginPresenter

class LoginActivity : AppCompatActivity(), ILoginView {
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var frameProgress: FrameLayout

    private lateinit var iLoginPresenter: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initPresenter()
        findView()
        setListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        iLoginPresenter.destroy()
    }

    private fun initPresenter() {
        iLoginPresenter = LoginPresenter(this)
    }

    private fun findView() {
        editTextUsername = findViewById(R.id.edit_username)
        editTextPassword = findViewById(R.id.edit_password)
        buttonLogin = findViewById(R.id.button_login)
        frameProgress = findViewById(R.id.frame_progress)
    }

    private fun setListener() {
        buttonLogin.setOnClickListener {
            iLoginPresenter.login(editTextUsername.text.toString().trim(), editTextPassword.text.toString().trim())
        }
    }

    override fun onClear() {
        editTextUsername.setText("")
        editTextPassword.setText("")
    }

    override fun onShowProgress() {
        frameProgress.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        frameProgress.visibility = View.GONE
    }

    override fun onUpdateInfo(username: String, age: Int) {
        Toast.makeText(this, "Username: $username, Password: $age", Toast.LENGTH_SHORT).show()
    }
}