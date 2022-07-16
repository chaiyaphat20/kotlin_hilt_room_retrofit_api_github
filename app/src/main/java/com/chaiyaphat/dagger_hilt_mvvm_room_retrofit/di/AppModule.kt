package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.di

import android.content.Context
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.db.AppDao
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.db.AppDatabase
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.network.RetroServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    //--------DB-----------//
    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getAppDBInstance(context)
    }

    //1 service from AppDao
    @Provides
    @Singleton
    fun getAppDao(appDatabase: AppDatabase): AppDao {
        return appDatabase.getAppDao()
    }

    //---------API-------//
    val BASE_URL =
        "https://api.github.com/search/"  //https://api.github.com/search/repositories?q=ny

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //1 service from RetroServicesApi
    @Provides
    @Singleton
    fun getGithubApi(retrofit: Retrofit): RetroServiceApi {
        return retrofit.create(RetroServiceApi::class.java)
    }


}