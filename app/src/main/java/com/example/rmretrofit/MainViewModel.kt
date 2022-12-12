package com.example.rmretrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmretrofit.network.ApiClient
import com.example.rmretrofit.network.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val repository:Repository = Repository(ApiClient.apiService)) : ViewModel() {

    private var _charactersLiveData = MutableLiveData< ScreenState<List<Character>?>>()
    val characterLiveData:LiveData<ScreenState<List<Character>?>>
    get() = _charactersLiveData


    fun fetchCharacter() {
        _charactersLiveData.postValue(ScreenState.Loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("MainViewModel", Thread.currentThread().name)
            try {
                val client = repository.getCharacters("1")
                _charactersLiveData.postValue(ScreenState.Success(client.result))
            } catch (e:Exception) {
                _charactersLiveData.postValue(ScreenState.Error(e.message.toString(), null))

            }
        }

    }
}