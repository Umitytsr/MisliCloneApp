package com.example.mislicloneapp.ui.detailer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mislicloneapp.databinding.FragmentMatchDetailerBinding
import com.example.mislicloneapp.util.formatTime

class MatchDetailerFragment : Fragment() {
    private lateinit var binding: FragmentMatchDetailerBinding
    private val args: MatchDetailerFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchDetailerBinding.inflate(inflater, container, false)
        matchDetailer()
        return binding.root
    }

    private fun matchDetailer() {
        with(binding) {
            val currentTimeMillis = System.currentTimeMillis()
            if (args.matchDetailer.matchState == "MS" || args.matchDetailer.matchState == "Bitti") {
                matchTime.text = "MS"
                matchScore.text =
                    "${args.matchDetailer.homeTeamScore} - ${args.matchDetailer.awayTeamScore}"
                halfTimeScore.text =
                    "${args.matchDetailer.homeTeamHalfTimeScore} - ${args.matchDetailer.awayTeamHalfTimeScore}"
            } else if (currentTimeMillis < args.matchDetailer.d) {
                matchTime.text = formatTime(args.matchDetailer.d)
                matchScore.text = "  v  "
                halfTimeScore.text = " v "
            } else {
                matchTime.text = args.matchDetailer.matchState
                matchScore.text =
                    if (args.matchDetailer.homeTeamScore != null && args.matchDetailer.awayTeamScore != null) "${args.matchDetailer.homeTeamScore} - ${args.matchDetailer.awayTeamScore}" else "  v  "
                halfTimeScore.text =
                    if (args.matchDetailer.homeTeamHalfTimeScore != null && args.matchDetailer.awayTeamHalfTimeScore != null) "${args.matchDetailer.homeTeamHalfTimeScore} - ${args.matchDetailer.awayTeamHalfTimeScore}" else ""
            }
            homeTeamName.text = args.matchDetailer.homeTeamName
            awayTeamName.text = args.matchDetailer.awayTeamName

        }
    }
}