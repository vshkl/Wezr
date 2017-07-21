package by.vshkl.android.wezr.util

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateTImeUtils {

    fun getReadableDateAndTime(dateTime: DateTime): String
            = DateTimeFormat.forPattern("dd MMM, HH:mm").print(dateTime)

    fun isAfterCurrentHour(dateTime: DateTime): Boolean
            = dateTime.withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).millis >= System.currentTimeMillis()
}