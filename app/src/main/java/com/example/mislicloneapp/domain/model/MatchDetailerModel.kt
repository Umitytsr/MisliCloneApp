package com.example.mislicloneapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchDetailerModel(
    val i: Int,
    val d: Long,
    val homeTeamName: String,
    val awayTeamName: String,
    val homeTeamScore: Int,
    val awayTeamScore: Int,
    val homeTeamHalfTimeScore : Int,
    val awayTeamHalfTimeScore: Int,
    val matchState : String
):Parcelable