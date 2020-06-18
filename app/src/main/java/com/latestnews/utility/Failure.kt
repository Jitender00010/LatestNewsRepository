package com.latestnews.utility

sealed class Failure : Exception() {

    class NetworkConnection(override var message : String = ""): Failure()
    class ServerError(override var message : String = "", val errorCode : String): Failure()
    class APIFailure(override var message : String = "") : Failure()
    class TemperedRequestFailure(override var message : String = "") : Failure()
    class TokenFailure(override var message : String = "") : Failure()
}
