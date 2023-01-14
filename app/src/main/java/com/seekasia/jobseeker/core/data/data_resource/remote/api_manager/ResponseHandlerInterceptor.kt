package com.seekasia.jobseeker.core.data.data_resource.remote.api_manager

import com.google.gson.Gson
import com.seekasia.jobseeker.core.data.data_resource.remote.exception.ApiException
import com.seekasia.jobseeker.core.data.data_resource.remote.response.ErrorResponse
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException


class ResponseHandlerInterceptor: Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.isSuccessful) {
            return response
        } else {
            val code = response.code
            val body: ResponseBody? = response.body

            if(body == null) throw ApiException(ErrorResponse(statusCode = code.toString(), message= "Something went wrong"))

            throw try {
                val gson = Gson()
                val errorResponse: ErrorResponse = gson.fromJson(response.body!!.charStream(), ErrorResponse::class.java)
                ApiException(errorResponse)
            } catch (ex: Exception) {
                ApiException(ErrorResponse(statusCode=code.toString(), message="Something went wrong"))
            } finally {
                body.close()
            }
        }
    }
}
