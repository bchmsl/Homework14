package com.bchmsl.homework14.ui.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bchmsl.homework14.network.RetrofitProvider
import com.bchmsl.homework14.utils.ResponseHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class PostsViewModel : ViewModel() {

    private val _response = MutableStateFlow<ResponseHandler>(ResponseHandler.Loading())
    val response: StateFlow<ResponseHandler> get() = _response

    fun getData() {
        viewModelScope.launch {
            val response = RetrofitProvider.makeClient().getPosts()
            if (response.isSuccessful && response.body()!= null){
                _response.emit(ResponseHandler.Success(response.body()))
            }else{
                _response.emit(ResponseHandler.Error(response.errorBody().toString()))
                Log.wtf("TAG", response.errorBody().toString())
            }
        }
    }
}