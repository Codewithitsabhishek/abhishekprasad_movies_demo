package com.example.codewithabhishek.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codewithabhishek.model.Movie
import com.example.codewithabhishek.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonalViewModel : ViewModel() {

    private val businessLiveData = MutableLiveData<List<Movie>>()
    private val horizontalBusinessLiveData = MutableLiveData<List<Movie>>()

    private val movieApi: MovieApi = RetrofitInstance.getApi()

    fun fetchMovies(searchQuery: String) {
        val apiKey = "b9bd48a6"
        val type = "movie"

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    movieApi.getMovies(searchQuery, apiKey, type)
                }
                businessLiveData.postValue(response.movies)
                horizontalBusinessLiveData.postValue(response.movies)
            } catch (e: Exception) {
                Log.e("PersonalViewModel", "Error fetching movies", e)
            }
        }
    }

    fun getBusinessLiveData(): LiveData<List<Movie>> = businessLiveData

    // LiveData for horizontal movies
    fun getHorizontalBusinessLiveData(): LiveData<List<Movie>> = horizontalBusinessLiveData
}
