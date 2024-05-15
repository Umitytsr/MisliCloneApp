package com.example.mislicloneapp.data.repo

import com.example.mislicloneapp.data.model.Data
import com.example.mislicloneapp.data.source.local.MatchLocalDataSource
import com.example.mislicloneapp.data.source.remote.MatchRemoteDataSource
import com.example.mislicloneapp.domain.model.MatchDetailerModel
import com.example.mislicloneapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MisliCloneRepository @Inject constructor(
    private val matchRemoteDataSource: MatchRemoteDataSource,
    private val matchLocalDataSource: MatchLocalDataSource
    ){

    suspend fun fetchAllMatch(): Flow<Resource<List<Data>>> = flow {
        Resource.Loading
        try {
            val propertiesMatchFromApi = matchRemoteDataSource.getAllMatchesProperties().data
            emit(Resource.Success(propertiesMatchFromApi))
        }catch (e:Exception){
            Resource.Error(e)
        }
    }

    fun allFavorites(): Flow<List<MatchDetailerModel>> = flow {
        emit(matchLocalDataSource.getAllFavoritePropertiesFromDb())
    }

    suspend fun addMatchToFavorites(favorite: MatchDetailerModel){
        matchLocalDataSource.insertFavoriteProperties(favorite)
    }

    suspend fun deleteMatchFromFavorites(favorite: MatchDetailerModel){
        matchLocalDataSource.deleteFavoriteProperties(favorite)
    }

    fun isFavorite(i: Int): Flow<Boolean> = flow {
        emit(matchLocalDataSource.isFavorite(i))
    }
}