package com.ng.ngleetcode.ui.page.web

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WebData(
  var title: String?,
  var url: String
) : Parcelable
