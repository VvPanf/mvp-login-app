package ru.vvpanf.mvp_login_app.login.presenter

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.json.JSONTokener
import ru.vvpanf.mvp_login_app.login.model.UserInfoModel
import ru.vvpanf.mvp_login_app.login.presenter.controller.LoginController
import ru.vvpanf.mvp_login_app.login.view.ILoginView

class LoginPresenter(var iLoginView: ILoginView?) : ILoginPresenter {
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun destroy() {
        iLoginView = null
    }

    override fun clear() {
        iLoginView?.onClear()
    }

    override fun showProgress() {
        iLoginView?.onShowProgress()
    }

    override fun hideProgress() {
        iLoginView?.onHideProgress()
    }

    override fun login(username: String, password: String) {
        showProgress()
        LoginController.requestLogin(username, password, object: LoginController.LoginControllerDelegate {
            override fun onSuccess(response: String) {
                Log.d("???", "onSuccess")
                val jsonObject = JSONTokener(response).nextValue() as JSONObject
                val userInfoModel = UserInfoModel(jsonObject.getString("username"), jsonObject.getInt("age"))
                scope.launch {
                    hideProgress()
                    iLoginView?.onUpdateInfo(userInfoModel.username, userInfoModel.age)
                }
            }

            override fun onFailed() {
                Log.d("???", "onFailed")
            }
        })

    }
}