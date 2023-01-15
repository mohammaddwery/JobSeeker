package com.seekasia.jobseeker.features.job.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SalaryRange(
    val min: Double,
    val max: Double,
): Parcelable