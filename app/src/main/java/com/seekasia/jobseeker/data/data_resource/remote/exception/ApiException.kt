package com.seekasia.jobseeker.data.data_resource.remote.exception

import com.seekasia.jobseeker.data.data_resource.remote.response.ErrorResponse
import java.io.IOException

data class ApiException(
    val error: ErrorResponse
) : IOException(error.message?:"Something went wrong")
