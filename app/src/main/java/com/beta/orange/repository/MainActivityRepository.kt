package com.beta.orange.repository

import com.beta.orange.datasource.UserEntityDataSource
import com.beta.orange.event.RequestEvent
import com.beta.orange.net.AppService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class MainActivityRepository(
    private val appService: AppService,
    private val UserEntityDataSource: UserEntityDataSource
) {

    fun request() = flow {
        emit(appService.getDomain())
    }


    fun request2() = flow<RequestEvent<Any>> {
        emit(RequestEvent.SuccessRequestEvent(data = appService.getDomain()))
    }.onStart {
        emit(RequestEvent.StartRequestEvent())
    }.catch {
        emit(RequestEvent.FailRequestEvent(Exception()))
    }
}