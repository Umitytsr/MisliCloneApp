package com.example.mislicloneapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mislicloneapp.data.model.At
import com.example.mislicloneapp.data.model.Data
import com.example.mislicloneapp.data.model.Ht
import com.example.mislicloneapp.data.model.Sc
import com.example.mislicloneapp.databinding.ItemRowMatchBinding
import com.example.mislicloneapp.domain.model.MatchDetailerModel
import com.example.mislicloneapp.domain.toMatchDetailer
import com.example.mislicloneapp.util.formatTime
import kotlinx.coroutines.flow.collectLatest

class MatchChildAdapter (
    private val organizedMatchList:List<Data>,
    private val viewModel: HomeFragmentViewModel,
    private val onClick: (MatchDetailerModel) -> Unit
):RecyclerView.Adapter<MatchChildAdapter.ChildViewHolder>(){

    inner class ChildViewHolder(private val binding: ItemRowMatchBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(sc: Sc?, ht: Ht?, at: At?, data : Data) {
            val currentTimeMillis = System.currentTimeMillis()
            with(binding) {
                if (sc?.abbr == "MS" || sc?.abbr == "Bitti"){
                    matchTime.text = "MS"
                    matchScore.text = if (sc?.ht?.r != null && sc.at?.r != null) "${sc.ht.r} - ${sc.at.r}" else "0 - 0"
                    halfTimeScore.text = if (sc?.ht?.ht != null && sc.at?.ht != null) "${sc.ht.ht} - ${sc.at.ht}" else "0 - 0"
                }else if(currentTimeMillis < data.d){
                    matchTime.text = formatTime(data.d)
                    matchScore.text = "  v  "
                    halfTimeScore.text = ""
                } else{
                    matchTime.text = sc?.abbr
                    matchScore.text = if (sc?.ht?.r != null && sc.at?.r != null) "${sc.ht.r} - ${sc.at.r}" else "  v  "
                    halfTimeScore.text = if (sc?.ht?.ht != null && sc.at?.ht != null) "${sc.ht.ht} - ${sc.at.ht}" else ""
                }
                homeTeamName.text = ht?.sn ?: "Unknown Team"
                awayTeamName.text = at?.sn ?: "Unknown Team"
                matchCard.setOnClickListener {
                    onClick(data.toMatchDetailer())
                }
                addFavorite.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked){
                        viewModel.addToFavorites(data.toMatchDetailer())
                        viewModel.getAllFavorites()
                    }else{
                        viewModel.deleteFromFavorites(data.toMatchDetailer())
                        viewModel.getAllFavorites()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowMatchBinding.inflate(inflater,parent,false)
        return ChildViewHolder(binding)
    }

    override fun getItemCount(): Int = organizedMatchList.size

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val item = organizedMatchList[position]
        holder.bind(item.sc,item.ht,item.at,item)
    }
}