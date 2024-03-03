package com.mbappe.network_graph_ql.di

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.util.DebugLogger
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpCallFactory
import com.mbappe.datasource.StationDataSource
import com.mbappe.network_graph_ql.BuildConfig
import com.mbappe.network_graph_ql.connectivity.ConnectivityObserver
import com.mbappe.network_graph_ql.connectivity.NetworkConnectivityObserver
import com.mbappe.network_graph_ql.datasource.StationNetworkDataSource
import com.mbappe.network_graph_ql.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    @Provides
    @Singleton
    fun apolloClient(
        okHttpCallFactory: Call.Factory,
    ): ApolloClient = ApolloClient.Builder()
        .serverUrl("https://openapi.radiofrance.fr/v1/graphql")
        .okHttpCallFactory(okHttpCallFactory)
        .build()

    @Provides
    @Singleton
    fun imageLoader(
        okHttpCallFactory: Call.Factory,
        @ApplicationContext application: Context,
    ): ImageLoader = ImageLoader.Builder(application)
        .callFactory(okHttpCallFactory)
        .components {
            add(SvgDecoder.Factory())
        }
        .apply {
            if (BuildConfig.DEBUG) {
                logger(DebugLogger())
            }
        }
        .build()

    @Provides
    fun providesStationDataSource(stationNetworkDataSource: StationNetworkDataSource): StationDataSource =
        stationNetworkDataSource

    @Provides
    fun providesConnectivityObserver(networkConnectivityObserver: NetworkConnectivityObserver) : ConnectivityObserver =
        networkConnectivityObserver
}