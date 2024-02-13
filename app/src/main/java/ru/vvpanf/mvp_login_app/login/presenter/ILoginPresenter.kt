package ru.vvpanf.mvp_login_app.login.presenter

interface ILoginPresenter {
    fun destroy()
    fun clear()
    fun showProgress()
    fun hideProgress()
    fun login(username: String, password: String)
}