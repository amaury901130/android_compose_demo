package com.rootstrap.android.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InputWrapper(
 val value: String = "",
 val errorId: Int? = null
) : Parcelable