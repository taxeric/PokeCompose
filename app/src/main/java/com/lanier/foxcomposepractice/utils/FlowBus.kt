package com.lanier.foxcomposepractice.utils

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import java.util.concurrent.ConcurrentHashMap

/**
 * Create by Eric
 * on 2022/4/21
 * 基于flow的事件总线
 */
object FlowBus {
    private var events = ConcurrentHashMap<Any, MutableSharedFlow<Any>>()

    /**
     * 发送
     */
    fun <T> with(objectKey: Any): MutableSharedFlow<T> {
        if (!events.containsKey(objectKey)) {
            events[objectKey] = MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)
        }
        return events[objectKey] as MutableSharedFlow<T>
    }

    /**
     * 接收
     */
    suspend fun <T> collect(key: Any, action: suspend (T) -> Unit){
        with<T>(key).collectLatest(action)
    }
}