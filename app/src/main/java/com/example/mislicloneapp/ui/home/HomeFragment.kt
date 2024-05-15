package com.example.mislicloneapp.ui.home

import MatchAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mislicloneapp.data.model.Data
import com.example.mislicloneapp.databinding.FragmentHomeBinding
import com.example.mislicloneapp.util.Resource
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel : HomeFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        collectMatchData()
        viewModel.getMatchData(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChipGroupListener()
    }

    private fun collectMatchData(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.matchResult.collectLatest { resource ->
                        when (resource){
                            is Resource.Loading -> {

                            }
                            is Resource.Success -> {
                                initRecyclerView(resource.data)
                            }
                            is Resource.Error -> {
                                Toast.makeText(requireContext(),"We have a problem",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerView (matchList: List<Data>){
        val _adapter = MatchAdapter(matchList)
        val _layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        with(binding.matchRecylerView){
            adapter = _adapter
            layoutManager = _layoutManager
            setHasFixedSize(true)
        }
    }

    private fun setupChipGroupListener() {
        binding.filterChipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            if (chip != null) {
                viewModel.getMatchData(true)
                Toast.makeText(requireContext(), "Biten Maçlar Listeleniyor", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getMatchData(false)
                Toast.makeText(requireContext(), "Tüm Maçlar Listeleniyor", Toast.LENGTH_SHORT).show()
            }
        }
    }
}