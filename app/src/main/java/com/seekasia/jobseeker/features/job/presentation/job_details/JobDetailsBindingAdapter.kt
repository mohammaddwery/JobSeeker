package com.seekasia.jobseeker.features.job.presentation.job_details

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.seekasia.jobseeker.core.Industries

@BindingAdapter("jobIndustry")
fun TextView.setJobIndustryTextValue(value: Industries) {
    this.text = value.name
}

@BindingAdapter("minSalary")
fun TextView.setJobMinSalaryTextValue(value: Double) {
    this.text = "$value \$"
}

@BindingAdapter("maxSalary")
fun TextView.setJobMaxSalaryTextValue(value: Double) {
    this.text = "$value \$"
}