package com.example.tweetsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsapp.repositories.TweetRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewmodel @Inject constructor(private val repositories: TweetRepositories): ViewModel() {
    val categories : StateFlow<List<String>>
        get() = repositories.categories
    init {
        viewModelScope.launch {
            repositories.getCategories()
        }
    }
}