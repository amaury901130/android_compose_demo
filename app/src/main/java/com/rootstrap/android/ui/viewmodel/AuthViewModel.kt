package com.rootstrap.android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rootstrap.android.network.managers.session.SessionManager
import com.rootstrap.android.network.managers.user.UserManager
import com.rootstrap.android.network.models.User
import com.rootstrap.android.ui.base.BaseViewModel
import com.rootstrap.android.util.InputWrapper
import com.rootstrap.android.util.NetworkState
import com.rootstrap.android.util.extensions.ApiErrorType
import com.rootstrap.android.util.extensions.ApiException
import com.rootstrap.android.util.extensions.isEmail
import com.rootstrap.android.util.isValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class AuthViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val userManager: UserManager,
) : BaseViewModel() {

    private val _state = MutableLiveData<AuthState>()
    val state: LiveData<AuthState>
        get() = _state

    private val _email = MutableStateFlow(InputWrapper())
    val email: StateFlow<InputWrapper> = _email

    private val _name = MutableStateFlow(InputWrapper())
    val name: StateFlow<InputWrapper> = _name

    private val _password = MutableStateFlow(InputWrapper())
    val password: StateFlow<InputWrapper> = _password

    val areInputsValid = combine(email, password, name) { email, password, name ->
        email.content.isEmail() && email.isValid() &&
                password.isValid() && name.isValid()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)

    fun onPasswordEntered(input: String) {
        //validate and send error
        val errorId = null
        _password.tryEmit(
            this.password.value.copy(content = input, errorId = errorId)
        )
    }

    fun onEmailEntered(input: String) {
        //validate and send error
        val errorId = null
        _email.tryEmit(
            this.email.value.copy(content = input, errorId = errorId)
        )
    }

    fun onNameEntered(input: String) {
        //validate and send error
        val errorId = null
        _name.tryEmit(
            this.name.value.copy(content = input, errorId = errorId)
        )
    }

    fun onSignUp() {
        signUp(
            User(
                email = email.value.content,
                password = password.value.content,
                firstName = name.value.content
            )
        )
    }

    fun onSignIn() {
        signIn(
            User(
                email = email.value.content,
                password = password.value.content
            )
        )
    }

    private fun signUp(user: User) {
        _networkState.value = NetworkState.loading
        viewModelScope.launch {
            val result = userManager.signUp(user = user)

            if (result.isSuccess) {
                result.getOrNull()?.value?.user?.let { user ->
                    sessionManager.signIn(user)
                }

                _networkState.value = NetworkState.idle
                _state.value = AuthState.signUpSuccess
            } else {
                handleError(result.exceptionOrNull())
            }
        }
    }

    private fun signIn(user: User) {
        _networkState.value = NetworkState.loading
        viewModelScope.launch {
            val result = userManager.signIn(user = user)
            if (result.isSuccess) {
                result.getOrNull()?.value?.user?.let { user ->
                    sessionManager.signIn(user)
                }

                _networkState.value = NetworkState.idle
                _state.value = AuthState.signInSuccess
            } else {
                handleError(result.exceptionOrNull())
            }
        }
    }

    private fun handleError(exception: Throwable?) {
        error = if (exception is ApiException && exception.errorType == ApiErrorType.apiError) {
            exception.message
        } else null

        _networkState.value = NetworkState.idle
        _networkState.value = NetworkState.error
        _state.value = AuthState.signUpFailure
    }
}

enum class AuthState {
    signUpFailure,
    signUpSuccess,
    signInFailure,
    signInSuccess
}
