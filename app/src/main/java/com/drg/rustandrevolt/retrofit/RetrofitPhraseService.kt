package com.drg.rustandrevolt.retrofit

import com.drg.rustandrevolt.retrofit.modelphrase.Phrase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface RetrofitPhraseService {
    @GET("phrase")
    suspend fun getPhraseOfDay(): Phrase
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitFraseServiceFactory{
    @Provides
    @Singleton
    fun makeRetrofitPhraseService(): RetrofitPhraseService {
        return Retrofit.Builder()
            .baseUrl("https://frasedeldia.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitPhraseService::class.java)
    }
}