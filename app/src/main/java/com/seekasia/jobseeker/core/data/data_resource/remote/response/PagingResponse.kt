package com.seekasia.jobseeker.core.data.data_resource.remote.response

class PagingResponse<T>(
    val page: Int,
    val size: Int,
    val total: Int,
    val hasNext: Boolean,
    val data: List<T>,
)