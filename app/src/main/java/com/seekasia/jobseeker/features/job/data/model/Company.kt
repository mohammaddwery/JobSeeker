package com.seekasia.jobseeker.features.job.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(
    @SerializedName("_id")
    val id: String,
    val name: String,
): Parcelable