
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mislicloneapp.data.model.Data
import com.example.mislicloneapp.databinding.ItemRowCountryBinding
import com.example.mislicloneapp.ui.home.HomeFragmentDirections
import com.example.mislicloneapp.ui.home.MatchChildAdapter

class MatchAdapter(
    private var matchList: List<Data>
    ) : RecyclerView.Adapter<MatchAdapter.MatchParentViewHolder>() {
    private var matchesByLeague: Map<String, List<Data>> = groupMatchesByLeague(matchList)

    private fun groupMatchesByLeague(matchList: List<Data>): Map<String, List<Data>> {
        return matchList.groupBy { it.to.n }
    }

    inner class MatchParentViewHolder(private val binding: ItemRowCountryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(leagueName: String, matches: List<Data>) {
            val _adapter = MatchChildAdapter(matches){matchDetailerModel ->
                val action = HomeFragmentDirections.actionHomeFragmentToMatchDetailerFragment(matchDetailerModel)
                binding.root.findNavController().navigate(action)
            }
            val _layoutManager = LinearLayoutManager(itemView.context,RecyclerView.VERTICAL,false)
            with(binding) {
                Glide.with(itemView.context)
                    .load(matches.first().to.flag)
                    .into(countryFlag)
                this.leagueName.text = leagueName
                leagueMatchesRecyclerView.adapter = _adapter
                leagueMatchesRecyclerView.layoutManager = _layoutManager
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchParentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemRowCountryBinding.inflate(inflater,parent,false)
        return MatchParentViewHolder(view)
    }

    override fun getItemCount(): Int = matchesByLeague.keys.size

    override fun onBindViewHolder(holder: MatchParentViewHolder, position: Int) {
        matchesByLeague.keys.elementAt(position).let { leagueName ->
            holder.bind(leagueName, matchesByLeague[leagueName]!!)
        }
    }
}