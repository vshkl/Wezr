package by.vshkl.android.wezr.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateFormatterUtils {

    fun getReadableDateAndTime(dateTime: DateTime): String {
        return DateTimeFormat.forPattern("dd MMM, HH:mm").print(dateTime)
    }
}