package com.example.mislicloneapp.di

import android.content.Context
import androidx.room.Room
import com.example.mislicloneapp.data.db.MatchDao
import com.example.mislicloneapp.data.db.MatchDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMovieDb(@ApplicationContext context: Context): MatchDb{
        return Room.databaseBuilder(
            context,
            MatchDb::class.java,
            "match_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMatchDao(matchDb: MatchDb): MatchDao{
        return matchDb.matchPropertyDao()
    }
}