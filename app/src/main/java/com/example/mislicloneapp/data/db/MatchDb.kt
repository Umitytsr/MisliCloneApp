package com.example.mislicloneapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mislicloneapp.domain.model.MatchDetailerModel

@Database (entities = [MatchDetailerModel::class], version = 1)

abstract class MatchDb: RoomDatabase() {
    abstract fun  matchPropertyDao(): MatchDao
}