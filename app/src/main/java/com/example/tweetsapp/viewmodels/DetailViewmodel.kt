package com.example.tweetsapp.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsapp.models.TweetListItem
import com.example.tweetsapp.repositories.TweetRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewmodel @Inject constructor(
    private val repositories: TweetRepositories,
    private val savedStateHandle: SavedStateHandle
    ): ViewModel() {
    val tweets : StateFlow<List<TweetListItem>>
        get() = repositories.tweets
    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "motivation"
            repositories.getTweets(category)
        }
    }
}