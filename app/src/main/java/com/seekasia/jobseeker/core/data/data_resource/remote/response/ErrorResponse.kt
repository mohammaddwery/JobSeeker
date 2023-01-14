package com.seekasia.jobseeker.core.data.data_resource.remote.response

data class ErrorResponse(
    val message: String?=null,
    val statusCode: String?=null,
    val error: String?=null
)