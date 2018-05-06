package comp_431.privacytracking.database;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;


public class DateTypeConvertors {

    @TypeConverter
    public static Date ToDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long ToLong(Date value) {
        return value == null ? null : value.getTime();
    }

}
