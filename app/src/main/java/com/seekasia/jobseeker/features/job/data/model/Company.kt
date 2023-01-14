package com.seekasia.jobseeker.features.job.data.model

import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("_id")
    val id: String,
    val name: String,
)