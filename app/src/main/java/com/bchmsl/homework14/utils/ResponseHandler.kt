package com.bchmsl.homework14.utils

import com.bchmsl.homework14.model.PostsResponse

sealed class ResponseHandler(
    open val isLoading: Boolean
) {
    class Success(val successData: PostsResponse?, override val isLoading: Boolean = false) :
        ResponseHandler(isLoading = false)

    class Error(val errorMessage: String?, override val isLoading: Boolean = false) :
        ResponseHandler(isLoading = false)

    class Loading(isLoading: Boolean = true) : ResponseHandler(isLoading)
}