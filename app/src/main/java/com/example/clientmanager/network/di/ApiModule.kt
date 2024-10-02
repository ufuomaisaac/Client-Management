package com.example.clientmanager.network.di

import com.example.clientmanager.utility.constants.Constants.CLIENT_MANAGER_BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module(/*includes = [
    NetworkModule::class
]*/)

@InstallIn(SingletonComponent::class)
class ApiModule {

    internal fun provideRetrofitBuilder(
        callAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory,
    ): Retrofit.Builder = Retrofit
        .Builder()
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)


    @Provides
    @Singleton
    @Named("Auth_Retrofit")
    internal fun provideAuthRetrofit(
        builder: Retrofit.Builder,
        @Named("BaseOkHttp") okhttp: OkHttpClient,
    ): Retrofit = builder
        .client(okhttp)
        .baseUrl(CLIENT_MANAGER_BASE_URL)
        .build()


    @Provides
    @Singleton
    internal fun provideRetrofit(
        builder: Retrofit.Builder,
        @Named("InterceptedOkHttp") okhttp: OkHttpClient,
    ): Retrofit = builder
        .client(okhttp)
        .baseUrl(CLIENT_MANAGER_BASE_URL)
        .build()

    @Provides
    @Singleton
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .also { it.level = BODY }

    @Provides
    @Singleton
    internal fun provideConverterFactory(): Converter.Factory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    internal fun provideCallAdapterFactory(): CallAdapter.Factory =
        RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io())

    @Provides
    @Singleton
    internal fun provideGson(): Gson =
        GsonBuilder().create()
}