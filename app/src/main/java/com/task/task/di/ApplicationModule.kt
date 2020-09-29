package com.task.task.di

import com.task.task.data.remote.GithubReposRemoteDS
import com.task.task.data.repository.GithubDataRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = "https://ghapi.huchen.dev/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = run {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit = run {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }


    @Provides
    fun provideGithubReposRemoteDS(retrofit: Retrofit): GithubReposRemoteDS =
        retrofit.create(GithubReposRemoteDS::class.java)

    @Provides
    fun provideGithubDataRepository(githubReposRemoteDS: GithubReposRemoteDS) = GithubDataRepository(githubReposRemoteDS)
}