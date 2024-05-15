package com.example.mislicloneapp.data.repo

import com.example.mislicloneapp.data.model.Data
import com.example.mislicloneapp.data.source.MatchDataSource
import com.example.mislicloneapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MisliCloneRepository @Inject constructor(private val matchDataSource: MatchDataSource){
    suspend fun fetchAllMatch(): Flow<Resource<List<Data>>> = flow {
        Resource.Loading
        try {
            val propertiesMatchFromApi = matchDataSource.getAllMatchesProperties().data
            emit(Resource.Success(propertiesMatchFromApi))
        }catch (e:Exception){
            Resource.Error(e)
        }
    }
}