package com.example.mislicloneapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mislicloneapp.domain.model.MatchDetailerModel

@Dao
interface MatchDao {
    @Query("SELECT * FROM favorite_match")
    suspend fun getAllFavorite(): List<MatchDetailerModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteProperties(propertiesFavorite: MatchDetailerModel)

    @Delete
    suspend fun deleteFavorite(propertiesFavorite: MatchDetailerModel)

    @Query("SELECT EXISTS (SELECT * FROM favorite_match WHERE i= :i)")
    suspend fun isFavorite(i:Int): Boolean
}