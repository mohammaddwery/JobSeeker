package com.seekasia.jobseeker.data.model

import com.google.gson.annotations.SerializedName
import com.seekasia.jobseeker.core.Industries
import com.seekasia.jobseeker.core.JobStatus
import com.seekasia.jobseeker.core.JobLocations
import java.util.*

data class JobModel(
    @SerializedName("_id")
    val id: String,
    val positionTitle: String,
    val description: String,
    val company: Company,
    val salaryRange: SalaryRange,
    val location: JobLocations,
    val industry: Industries,
    val status: JobStatus,
    val createdAt: Date,
)