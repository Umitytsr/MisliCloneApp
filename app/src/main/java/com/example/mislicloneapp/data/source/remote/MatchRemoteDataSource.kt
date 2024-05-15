package com.example.mislicloneapp.data.source.remote

import com.example.mislicloneapp.data.service.MisliApiService
import javax.inject.Inject

class MatchRemoteDataSource @Inject constructor(private val misliApiService: MisliApiService){
    suspend fun getAllMatchesProperties() = misliApiService.getAllMatches()
}