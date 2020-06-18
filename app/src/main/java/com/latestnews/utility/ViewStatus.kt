package com.callmanagerfinal.utility

sealed class ViewStatus {

    data class SUCCESS(val message : String) : ViewStatus()
    data class FAIL(val failure : Failure) : ViewStatus()
    data class LOADING(val message : String) : ViewStatus()

    //use this generic object when no customization needed
    companion object {

        val LOADING = ViewStatus.LOADING("loading......")
        val SUCCESS = ViewStatus.SUCCESS("SUCCESS")

    }
}