package com.example.endemicsid

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hewan(
    val nama: String,
    val ilmiah: String,
    val deskripsi: String,
    val foto: String
) : Parcelable
