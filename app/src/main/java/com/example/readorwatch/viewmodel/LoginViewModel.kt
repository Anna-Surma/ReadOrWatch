package com.example.readorwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.readorwatch.LoginValidator.checkEmailError
import com.example.readorwatch.LoginValidator.checkPasswordError
import com.example.readorwatch.LoginValidator.checkSecondPasswordError
import com.example.readorwatch.R
import com.example.readorwatch.api.ApiClient
import com.example.readorwatch.model.LogInEvent
import com.example.readorwatch.model.SignInResponse
import com.example.readorwatch.model.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _logInEvent = MutableLiveData(LogInEvent(false, null))
    val logInEvent: LiveData<LogInEvent> = _logInEvent

    private val _emailErrorMessage = MutableLiveData<Int?>(null)
    val emailErrorMessage: LiveData<Int?> = _emailErrorMessage
    private val _passwordErrorMessage = MutableLiveData<Int?>(null)
    val passwordErrorMessage: LiveData<Int?> = _passwordErrorMessage
    private val _repeatPasswordErrorMessage = MutableLiveData<Int?>(null)
    val repeatPasswordErrorMessage: LiveData<Int?> = _repeatPasswordErrorMessage

    private val _emailHolder = MutableLiveData("")
    val emailHolder: LiveData<String> = _emailHolder
    private val _passwordHolder = MutableLiveData("")
    val passwordHolder: LiveData<String> = _passwordHolder
    private val _secondPasswordHolder = MutableLiveData("")
    val secondPasswordHolder: LiveData<String> = _secondPasswordHolder

    private val _loadingStatus = MutableLiveData<Boolean>(false)
    val loadingStatus: LiveData<Boolean> = _loadingStatus

    fun updateEmail(newEmail: String) {
        _emailHolder.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _passwordHolder.value = newPassword
    }

    fun updateSecondPassword(newSecondPassword: String) {
        _secondPasswordHolder.value = newSecondPassword
    }

    fun onSignInButtonClicked(
        email: String = emailHolder.value.toString().lowercase(),
        password: String = passwordHolder.value.toString()
    ) {
        _emailErrorMessage.value = null
        _passwordErrorMessage.value = null
        _emailErrorMessage.value = checkEmailError(email)
        _passwordErrorMessage.value = checkPasswordError(password)
        if (_emailErrorMessage.value == null && _passwordErrorMessage.value == null) {
            loginUser(email, password)
        }
    }

    fun onRegisterButtonClicked(
        email: String = emailHolder.value.toString().lowercase(),
        password: String = passwordHolder.value.toString(),
        secondPassword: String = secondPasswordHolder.value.toString()
    ) {
        _emailErrorMessage.value = null
        _passwordErrorMessage.value = null
        _repeatPasswordErrorMessage.value = null
        _emailErrorMessage.value = checkEmailError(email)
        _passwordErrorMessage.value = checkPasswordError(password)
        _repeatPasswordErrorMessage.value = checkSecondPasswordError(password, secondPassword)
        if (emailErrorMessage.value == null && passwordErrorMessage.value == null && repeatPasswordErrorMessage.value == null) {
            registerUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {

        _loadingStatus.value = true

        ApiClient().getService()?.signIn(email, password)
            ?.enqueue(object : Callback<SignInResponse> {
                override fun onResponse(
                    call: Call<SignInResponse>,
                    response: Response<SignInResponse>
                ) {
                    val loginResponse = response.body()
                    if (response.isSuccessful) {
                        if (loginResponse?.token != null) {
                            _loadingStatus.value = false
                            _logInEvent.value = LogInEvent(true, loginResponse.token)
                        }
                    } else {
                        _loadingStatus.value = false
                        assignFullError(R.string.incorrectPasswordOrEmailError)
                    }
                }

                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    _loadingStatus.value = false
                    assignFullError(R.string.serverConnectionFailedError)
                }
            })
    }

    private fun registerUser(email: String, password: String) {
        ApiClient().getService()?.signUp(email, email, password)
            ?.enqueue(object : Callback<SignUpResponse> {
                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    if (response.isSuccessful) {
                        loginUser(email, password)
                    } else {
                        assignFullError(R.string.incorrectPasswordOrEmailError)
                    }
                }

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    assignFullError(R.string.serverConnectionFailedError)
                }
            })
    }

    private fun assignFullError(errorMessage: Int?) {
        _emailErrorMessage.value = errorMessage
        _passwordErrorMessage.value = errorMessage
    }
}