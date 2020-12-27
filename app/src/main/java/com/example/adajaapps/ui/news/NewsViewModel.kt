package com.example.adajaapps.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adajaapps.data.model.ActionState
import com.example.adajaapps.data.model.News
import com.example.adajaapps.data.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.http.Query

class NewsViewModel : ViewModel() {
    private val repo: NewsRepository by lazy { NewsRepository() }

    val loading = MutableLiveData(false)
    val actionState = MutableLiveData<ActionState<*>>()

    val listResp = MutableLiveData<List<News>>()
    val detailResp = MutableLiveData<News>()
    val searchResp = MutableLiveData<List<News>>()

    val url = MutableLiveData("")
    val query = MutableLiveData("")

    fun listNews() {
        viewModelScope.launch {
            loading.value = true
            val resp = repo.listNews()
            actionState.value = resp
            listResp.value = resp.data
            loading.value = false
        }
    }

    fun detailNews(url: String? = this.url.value) {
        url?.let {
            viewModelScope.launch {
                loading.value = true
                val resp = repo.detailNews(it)
                actionState.value = resp
                detailResp.value = resp.data
                loading.value = false

            }
        }
    }

    fun searchNews(query: String? = this.query.value) {
        query?.let {
            viewModelScope.launch {
                loading.value = true
                val resp = repo.searchNews(it)
                actionState.value = resp
                searchResp.value = resp.data
                loading.value = false
            }
        }
    }


}