package com.example.mislicloneapp.data.source.local

import com.example.mislicloneapp.data.db.MatchDao
import com.example.mislicloneapp.domain.model.MatchDetailerModel
import javax.inject.Inject

class MatchLocalDataSource @Inject constructor(private val matchDao: MatchDao){

    suspend fun insertFavoriteProperties(favoriteProperties: MatchDetailerModel){
        matchDao.insertFavoriteProperties(favoriteProperties)
    }

    suspend fun deleteFavoriteProperties(favoriteProperties: MatchDetailerModel){
        matchDao.deleteFavorite(favoriteProperties)
    }

    suspend fun getAllFavoritePropertiesFromDb(): List<MatchDetailerModel>{
        return matchDao.getAllFavorite()
    }

    suspend fun isFavorite(i: Int): Boolean{
        return matchDao.isFavorite(i)
    }
}