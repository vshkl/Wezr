package by.vshkl.android.wezr.util

import by.vshkl.android.wezr.data.model.City
import org.json.JSONArray

object JsonUtils {

    fun cityListFromJsonArray(jsonString: String?): List<City> {
        val jsonArray = JSONArray(jsonString)

        return (0..jsonArray.length() - 1).map {
            City(jsonArray.getJSONObject(it).getInt("id"), jsonArray.getJSONObject(it).getString("city"))
        }
    }

}