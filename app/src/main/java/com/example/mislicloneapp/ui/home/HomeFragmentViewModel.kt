package com.example.mislicloneapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mislicloneapp.data.model.Data
import com.example.mislicloneapp.data.repo.MisliCloneRepository
import com.example.mislicloneapp.domain.model.MatchDetailerModel
import com.example.mislicloneapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val misliCloneRepository: MisliCloneRepository): ViewModel() {
    private val _matchResult = MutableStateFlow<Resource<List<Data>>>(Resource.Loading)
    val matchResult : StateFlow<Resource<List<Data>>> = _matchResult.asStateFlow()

    private val _favoriteMatchResult = MutableStateFlow<List<Data>>(emptyList())
    val favoriteMatchResult = _favoriteMatchResult.asStateFlow()

    fun getMatchData(chipChecked : Boolean){
        viewModelScope.launch (Dispatchers.IO){
            misliCloneRepository.fetchAllMatch().collectLatest {
                if (it is Resource.Success){
                    val sortedDate = it.data.sortedBy { match -> match.d }

                    val sortedAndFilteredMatches = sortedDate.sortedBy { match -> match.to.p }

                    if (chipChecked == true){
                        val chipCheck = sortedAndFilteredMatches
                            .filter { match -> match.sc?.abbr == "MS" || match.sc?.abbr == "Bitti" }
                        _matchResult.emit(Resource.Success(chipCheck))
                    }else{
                        _matchResult.emit(Resource.Success(sortedAndFilteredMatches))
                    }
                } else {
                    _matchResult.emit(it)
                }
            }
        }
    }

    fun getAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val allMathesResource = misliCloneRepository.fetchAllMatch().first()
            val favoriteMatchesResource = misliCloneRepository.allFavorites().first()
            val favoriteMatches = ArrayList<Data>()
            if (allMathesResource is Resource.Success){
                for (i in allMathesResource.data){
                    for (j in favoriteMatchesResource){
                        if (i.i == j.i){
                            favoriteMatches.add(i)
                        }
                    }
                }
            }
            val sortedDate = favoriteMatches.sortedBy { match -> match.d }
            _favoriteMatchResult.emit(sortedDate)
        }
    }

    fun addToFavorites(matchDetailerModel: MatchDetailerModel){
        viewModelScope.launch {
            misliCloneRepository.addMatchToFavorites(matchDetailerModel)
            getAllFavorites()
        }
    }

    fun deleteFromFavorites(matchDetailerModel: MatchDetailerModel){
        viewModelScope.launch{
            misliCloneRepository.deleteMatchFromFavorites(matchDetailerModel)
            getAllFavorites()
        }
    }
}