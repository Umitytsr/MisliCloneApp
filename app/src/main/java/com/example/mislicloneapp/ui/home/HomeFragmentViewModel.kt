package com.example.mislicloneapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mislicloneapp.data.model.Data
import com.example.mislicloneapp.data.repo.MisliCloneRepository
import com.example.mislicloneapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val misliCloneRepository: MisliCloneRepository): ViewModel() {
    private val _matchResult = MutableStateFlow<Resource<List<Data>>>(Resource.Loading)
    val matchResult : StateFlow<Resource<List<Data>>> = _matchResult.asStateFlow()

    fun getMatchData(chipChecked : Boolean){
        viewModelScope.launch (Dispatchers.IO){
            misliCloneRepository.fetchAllMatch().collectLatest {
                if (it is Resource.Success){
                    val sortedDate = it.data.sortedBy { match -> match.d }

                    val sortedAndFilteredMatches = sortedDate.sortedBy { match -> match.to?.p }

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
}