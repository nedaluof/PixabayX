package com.nedaluof.data.di

import android.content.Context
import androidx.room.Room
import com.nedaluof.data.datasource.local.db.PixabayXDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By NedaluOf - 7/22/2023.
 */
@InstallIn(SingletonComponent::class)
@Module
object LocalDataSourceModule {
  @Singleton
  @Provides
  fun providePixabayXDatabase(
    @ApplicationContext context: Context
  ): PixabayXDatabase = Room.databaseBuilder(
    context,
    PixabayXDatabase::class.java,
    PixabayXDatabase.DATABASE_NAME
  ).createFromAsset(PixabayXDatabase.PRE_POPULATE_DATABASE_PATH).build()
}