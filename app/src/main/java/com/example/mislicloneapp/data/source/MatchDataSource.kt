package com.example.mislicloneapp.data.source

import com.example.mislicloneapp.data.service.MisliApiService
import javax.inject.Inject

class MatchDataSource @Inject constructor(private val misliApiService: MisliApiService){
    suspend fun getAllMatchesProperties() = misliApiService.getAllMatches()
}