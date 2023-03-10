package com.seekasia.jobseeker.core.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    inline fun <T> launchPagingAsync(
        crossinline execute: suspend () -> Flow<T>,
        crossinline onSuccess: (StateFlow<T>) -> Unit,
        crossinline onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val result = execute()
                onSuccess(result.stateIn(this))
            } catch (ex: Exception) {
                onError(ex.message?:"Something went wrong")
            }
        }
    }
}