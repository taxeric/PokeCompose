package com.lanier.foxcomposepractice.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/18 15:27
 * Desc  : base viewmodel
 */
abstract class BaseViewModel: ViewModel() {

    open fun mxLaunch(
        block: suspend () -> Unit,
        error: suspend (Throwable) -> Unit,
        complete: suspend () -> Unit
    ){
        //运行在子线程
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception){
                error(e)
            } finally {
                complete()
            }
        }
    }
}