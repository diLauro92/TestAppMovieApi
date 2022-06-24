package com.dilauro.testmovie.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dilauro.testmovie.app.App
import com.dilauro.testmovie.const.PRIVATE_API_KEY
import com.dilauro.testmovie.data.dataclasses.Movies
import com.dilauro.testmovie.di.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var apiService: ApiService
    private var liveDataList: MutableLiveData<Movies>

    init {
        (application as App).getApiServiceComponent().inject(this)
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<Movies> {
        return liveDataList
    }

    fun getApiCall(offset: Int) {
        val call: Call<Movies> = apiService.getMovies(PRIVATE_API_KEY, offset)
        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.body() != null) {
                    liveDataList.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                liveDataList.postValue(null)
            }
        })
    }
}