package com.mbappe.network_graph_ql.interceptor

import com.mbappe.network_graph_ql.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            // AUTH_TOKEN_HEADER is set in local.properties
            .addHeader("X-Token", BuildConfig.AUTH_TOKEN_HEADER)
            .build()
        return chain.proceed(request)
    }
}
