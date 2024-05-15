package com.example.mislicloneapp.data.service

import com.example.mislicloneapp.data.model.MatchResponse
import retrofit2.http.GET

interface MisliApiService {
    @GET("statistics/sport/SOCCER/matches")
    suspend fun getAllMatches(): MatchResponse
}