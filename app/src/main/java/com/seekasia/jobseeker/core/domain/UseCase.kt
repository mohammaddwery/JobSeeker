package com.seekasia.jobseeker.core.domain

interface UseCase<Output, Param> {
    suspend fun call(param: Param): Output
}

class NoParam