package com.rootstrap.android.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InputWrapper(
 val content: String = "",
 val errorId: Int? = null
) : Parcelable

fun InputWrapper.isValid() : Boolean = this.content.trim().isNotEmpty() && this.errorId != null