package com.rootstrap.android.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.rootstrap.android.util.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * A [ViewModel] base class
 * implement app general LiveData as Session or User
 * **/
open class BaseViewModel : ViewModel(), LifecycleObserver {
    var error: String? = null

    protected val _networkState = MutableStateFlow(NetworkState.idle)
    val networkState: StateFlow<NetworkState>
        get() = _networkState
}
