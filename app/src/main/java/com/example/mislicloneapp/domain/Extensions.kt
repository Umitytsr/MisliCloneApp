package com.example.mislicloneapp.domain

import com.example.mislicloneapp.data.model.Data
import com.example.mislicloneapp.domain.model.MatchDetailerModel

fun Data.toMatchDetailer(): MatchDetailerModel {
    return MatchDetailerModel(
        i = this.i ?: 0,
        d = this.d ?: 0,
        homeTeamName = this.ht?.sn ?: "Unknown Team",
        awayTeamName = this.at?.sn ?: "Unknown Team",
        homeTeamScore = this.sc.ht?.r ?: 0,
        awayTeamScore = this.sc.at?.r ?: 0,
        homeTeamHalfTimeScore = this.sc.ht?.ht ?: 0,
        awayTeamHalfTimeScore = this.sc.at?.ht ?: 0,
        matchState = this.sc.abbr ?: "MS"
    )
}