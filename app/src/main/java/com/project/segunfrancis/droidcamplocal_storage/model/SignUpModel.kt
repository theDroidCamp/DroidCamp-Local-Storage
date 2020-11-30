package com.project.segunfrancis.droidcamplocal_storage.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by SegunFrancis
 */

@Parcelize
data class SignUpModel(
    val name: String,
    val email: String,
    val school: String,
    val password: String
): Parcelable