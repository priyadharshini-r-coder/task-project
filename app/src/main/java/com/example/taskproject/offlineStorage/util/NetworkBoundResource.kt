package com.example.taskproject.offlineStorage.util

import com.bumptech.glide.load.engine.Resource
import kotlinx.coroutines.flow.*

inline fun <ResultType,RequestType>  networkBoundResource(
    crossinline query:()-> Flow<ResultType>,
    crossinline fetch:suspend () ->RequestType,
    crossinline saveFetchResult:suspend(RequestType) ->Unit,
    crossinline shouldFetch:(ResultType) ->Boolean={ true}
)= flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(com.example.taskproject.offlineStorage.util.Resource.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map {
                com.example.taskproject.offlineStorage.util.Resource.Success(it)
            }
        } catch (throwable: Throwable) {
            query().map {
                com.example.taskproject.offlineStorage.util.Resource.Error(throwable, it)
            }
        }
    } else {
        query().map {
            com.example.taskproject.offlineStorage.util.Resource.Success(it)
        }
    }
    emitAll(flow)
}

