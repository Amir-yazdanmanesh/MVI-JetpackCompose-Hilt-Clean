package com.yazdanmanesh.githubcompose.di

import android.app.Application
import androidx.room.Room
import com.yazdanmanesh.githubcompose.data.local.GithubDao
import com.yazdanmanesh.githubcompose.data.local.GithubDatabase
import com.yazdanmanesh.githubcompose.data.remote.Endpoints
import com.yazdanmanesh.githubcompose.data.remote.GithubApi
import com.yazdanmanesh.githubcompose.domain.mappers.GithubMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): GithubDatabase {
        return Room.databaseBuilder(app, GithubDatabase::class.java, "github_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: GithubDatabase): GithubDao = db.dao

    @Provides
    @Singleton
    fun provideMapper(): GithubMapper = GithubMapper()
}
