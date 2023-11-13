package ru.vvpanf.mvp_login_app.login.view

interface ILoginView {
    fun onClear()
    fun onShowProgress()
    fun onHideProgress()
    fun onUpdateInfo(username: String, age: Int)
}