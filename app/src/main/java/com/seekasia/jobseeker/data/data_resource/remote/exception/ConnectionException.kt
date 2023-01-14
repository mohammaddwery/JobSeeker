package com.seekasia.jobseeker.data.data_resource.remote.exception

import java.io.IOException

class NoConnectionException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}