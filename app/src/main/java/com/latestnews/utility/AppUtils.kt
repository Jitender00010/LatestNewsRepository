package com.latestnews.utility

import java.text.SimpleDateFormat
import java.util.*

object AppUtils {

    fun getCurrentTime () : String{
        return  SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format( Date())
    }

    fun timeDifference(time1 : String?, time2 : String?) : Int {
        val format = SimpleDateFormat("HH:mm:ss")
        val date1 = format.parse(time1)
        val date2 = format.parse(time2)
        val mills = date1.time - date2.time
        val hours = (mills / (1000 * 60 * 60)).toInt()
        val mins = (mills / (1000 * 60) % 60).toInt()
        return Math.abs(hours)
    }

}