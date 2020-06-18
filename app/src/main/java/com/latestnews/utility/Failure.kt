package  com.callmanagerfinal.utility

sealed class Failure : Exception() {

    class NetworkConnection(override var message : String = ""): Failure()
    class ServerError(override var message : String = "", val errorCode : Int = 500): Failure()
    class APIFailure(override var message : String = "") : Failure()
    class TemperedRequestFailure(override var message : String = "") : Failure()
    class TokenFailure(override var message : String = "") : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure( override  var message : String = ""): Failure()

}
