package com.example.mislicloneapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_match")
data class MatchDetailerModel(
    @PrimaryKey
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