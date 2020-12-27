package com.example.adajaapps.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String = "",
    val href: String = "",
    val ingredients: String = "",
    val thumbnail: String = ""
) : Parcelable
