package by.vshkl.android.wezr.data.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;


import static by.vshkl.android.wezr.data.model.AlertType.RADAR;
import static by.vshkl.android.wezr.data.model.AlertType.WEATHER_FETCH;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@IntDef({WEATHER_FETCH, RADAR})
public @interface AlertType {
    int WEATHER_FETCH = 0;
    int RADAR = 1;
}
