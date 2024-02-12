package com.drg.rustandrevolt.retrofit

import android.app.Application
import com.drg.rustandrevolt.retrofit.model.RemoteResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface RetrofitService {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): RemoteResult
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceFactory{
    @Provides
    @Singleton
    fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}