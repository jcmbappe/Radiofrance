package com.mbappe.network_graph_ql.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.mbappe.network_graph_ql.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@InstallIn(SingletonComponent::class)
@Module
object Network {

    @Provides
    fun apolloClient(): ApolloClient = ApolloClient.Builder()
        .serverUrl("https://openapi.radiofrance.fr/v1/graphql")
        .okHttpClient(
            OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor())
                .build()
        )
        .build()

}