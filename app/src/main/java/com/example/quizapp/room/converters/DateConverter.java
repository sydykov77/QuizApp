package com.example.quizapp.room.converters;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Long toJsonDate(@Nullable Date date){
        if (date == null)return null;
        return date.getTime();
    }
    @TypeConverter
    public static Date fromJsonDate(@Nullable Long timestamp){
        if (timestamp == null)return null;
        return new Date(timestamp);
    }
}
