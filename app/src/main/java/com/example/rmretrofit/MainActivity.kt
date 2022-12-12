package com.example.rmretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rmretrofit.network.ApiClient
import com.example.rmretrofit.network.Character
import com.example.rmretrofit.network.CharacterResponse
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val adapter: MainAdapter by lazy { MainAdapter() }
    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.charactersRv)
        recyclerView?.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView?.adapter = adapter

        viewModel.characterLiveData.observe(this) { state ->
            Log.d("MainActivity", state.javaClass.simpleName)
            processCharactersResponse(state)
        }

        viewModel.fetchCharacter()
    }

    private fun processCharactersResponse (state: ScreenState<List<Character>?>) {
        val pb = findViewById<ProgressBar>(R.id.progressBar)
        when (state) {
            is ScreenState.Loading -> {
                pb.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                state.data?.let { list ->
                    pb.visibility = View.GONE
                    adapter.setList(list)
                }
            }
            is ScreenState.Error -> {
                pb.visibility = View.GONE
                val view = pb.rootView
                Snackbar.make(view , state.message!!, Snackbar.LENGTH_LONG).show()

            }
        }
    }
}