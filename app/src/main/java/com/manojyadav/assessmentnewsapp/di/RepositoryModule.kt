
package com.manojyadav.assessmentnewsapp.di

import android.content.Context
import com.manojyadav.assessmentnewsapp.data.local.NewsDao
import com.manojyadav.assessmentnewsapp.data.local.NewsDatabase
import com.manojyadav.assessmentnewsapp.network.api.ApiHelper
import com.manojyadav.assessmentnewsapp.network.repository.INewsRepository
import com.manojyadav.assessmentnewsapp.network.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        NewsDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase) = db.getNewsDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: ApiHelper,
        localDataSource: NewsDao
    ) = NewsRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideINewsRepository(repository: NewsRepository): INewsRepository = repository
}