package com.beta.orange.event

sealed class RequestEvent<T> private constructor(
    var exception: Exception? = null,
    var data: T? = null,
) {

    class StartRequestEvent<T> : RequestEvent<T>()
    class SuccessRequestEvent<T>(data: T?) : RequestEvent<T>(data = data)
    class FailRequestEvent<T>(exception: Exception?) : RequestEvent<T>(exception = exception)

}
